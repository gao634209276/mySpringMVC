package spring.hessian.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parse {
    private static Log log = LogFactory.getLog(Parse.class);
    private static DateFormat formatter = new SimpleDateFormat("yyyyMMdd");


    public static String parseSQL(Map<String, String> label) {
        //
        List<String> useFrequencyLabel = new ArrayList<>(Arrays.asList("netUseFrequ", "mobUseFrequ"));
        //
        List<String> baseInfoLabel = new ArrayList<>(Arrays.asList(
                "province", "netType", "payType", "packageId", "age", "netTime", "netAge"));

        StringBuffer filterBase = new StringBuffer();
        StringBuffer filterUseWeb = new StringBuffer();
        StringBuffer filterUseMob = new StringBuffer();
        /**
         * 标签条件：
         * province:a,b,c
         * netType:2G,3G,4G
         * payType:0,1
         * packageId:
         * age:-50
         * netTime:20160501-
         * netAge:
         * netUseFrequ:1-4,10-
         * mobUseFrequ:4,
         */
        for (String key : label.keySet()) {
            /**
             * 用户基本信息数据表
             * table:t_bigdata_user_mon
             * date         月份
             * province_id  省份
             * number       电话号码
             * arpu         arpu值
             * user_name    客户姓名
             * card_id      证件号码
             * biz_type     业务类型
             * package_type 主套餐名称
             * frist_time   入网时间
             */
            if (baseInfoLabel.contains(key)) {
                parseBaseInfo(key.trim(), label.get(key).trim(), filterBase);
            }
            /**
             * 用户使用手短网厅频次数据表
             * table:t_mrt_zhfw_user_mon
             * mobile,province,city,mon_01-mob_12,application
             * partition:
             * application=mob
             * application=sms
             * application=web
             */
            else if (useFrequencyLabel.contains(key)) {
                // 1,netUseFrequ
                if (key.trim().equals("netUseFrequ")) {
                    filterUseWeb.append("select mobile from t_mrt_zhfw_user_mon ")
                            .append(parseUseFrequency("web", label.get(key)));
                }
                // 2,mobUseFrequ
                else if (key.trim().equals("mobUseFrequ")) {
                    filterUseMob.append("select mobile from t_mrt_zhfw_user_mon ")
                            .append(parseUseFrequency("mob", label.get(key)));
                }

            } else {
                log.error("not match label : " + key);
            }

        }

        // 组拼每个表对应的子查询,sqlA,sqlB
        String sqlA = "";
        String sqlB = "";
        String sqlBweb = filterUseWeb.toString();
        String sqlBmob = filterUseMob.toString();

        if (filterBase.toString().length() > 0) {
            sqlA = "select number as mobile from t_bigdata_user_mon where 1=1 " + filterBase.toString();
        }
        if (sqlBweb.length() > 0 && sqlBmob.length() > 0) {
            sqlB = sqlBweb + " intersect " + sqlBmob;
        } else if (sqlBweb.length() > 0) {
            sqlB = sqlBweb;
        } else {
            sqlB = sqlBmob;
        }

        // 最终组拼的SQL
        StringBuffer sql = new StringBuffer();
        if (sqlA.length() > 0 && sqlB.length() > 0) {
            sql.append("select a.mobile from (").append(sqlA).append(") a ")
                    .append(" join (").append(sqlB).append(")b")
                    .append(" on a.mobile = b.mobile");
        } else if (sqlB.length() > 0) {
            sql.append("select mobile from (").append(sqlB).append(")");
        } else {
            sql.append(sqlA);
        }
        return sql.toString();
    }


    /**
     * 根据标签条件解析符合sparkSQL的查询过滤解析，
     * 该sql最终查询hive表t_bigdata_user_mon
     *
     * @param labelK  标签key值
     * @param labelV  标签key对应的value值
     * @param filterA 需要操作的stringBuf
     */
    public static void parseBaseInfo(String labelK, String labelV, StringBuffer filterA) {
        // 1.age,eg:0-30,只基于18位,15位有效身份证计算age
        if (labelK.equals("age")) {
            String getBirth = " and" +
                    " case length(card_id) when 18 then mark(card_id,7,8)" +
                    " when 15 then concat('19',mark(card_id,7,6))" +
                    " else null end ";

            String ageAreaBegin = getSplit(labelV, "-", 0);
            String ageAreaEnd = getSplit(labelV, "-", 1);
            if (!ageAreaEnd.equals("")) {
                String birthdayBegin = getTime((0 - Integer.valueOf(ageAreaEnd)) * 12);
                filterA.append(getBirth).append(">='").append(birthdayBegin).append("'");
                log.info(getBirth + ">=" + birthdayBegin);
            }
            if (!ageAreaBegin.equals("")) {
                String birthdayEnd = getTime((0 - Integer.valueOf(ageAreaBegin)) * 12);
                filterA.append(getBirth).append("<='").append(birthdayEnd).append("'");
                log.info(getBirth + "<=" + birthdayEnd);
            }
        }


        //2,netTime,eg: 20110202-20150505,8为时间格式数字,最小单位到天
        if (labelK.equals("netTime")) {
            String netTimeAreaBegin = getSplit(labelV, "-", 0);
            String netTimeAreaEnd = getSplit(labelV, "-", 1);
            if (!netTimeAreaBegin.equals("")) {
                filterA.append(" and frist_time>='").append(netTimeAreaBegin).append("'");
                log.info(" and frist_time>=" + netTimeAreaBegin);
            }
            if (!netTimeAreaEnd.equals("")) {
                filterA.append(" and frist_time<='").append(netTimeAreaEnd).append("'");
                log.info(" and frist_time<='" + netTimeAreaEnd + "'");
            }
        }

        //3,netAge,eg: 5-10,年为单位
        if (labelK.equals("netAge")) {
            String netAgeAreaBegin = getSplit(labelV, "-", 0);
            String netAgeAreaEnd = getSplit(labelV, "-", 1);

            if (!netAgeAreaBegin.equals("")) {
                String netAgeTimeEnd = getTime((0 - Integer.valueOf(netAgeAreaBegin)) * 12);
                filterA.append(" and frist_time<='").append(netAgeTimeEnd).append("'");
                log.info(" and frist_time<='" + netAgeTimeEnd + "'");
            }
            if (!netAgeAreaEnd.equals("")) {
                String netAgeTimeBegin = getTime((0 - Integer.valueOf(netAgeAreaEnd)) * 12);
                filterA.append(" and frist_time>='").append(netAgeTimeBegin).append("'");
                log.info(" and frist_time>='" + netAgeTimeBegin + "'");
            }
        }

        //4,netType,eg:2G,3G,4G
        if (labelK.equals("netType")) {
            //biz_type
            filterA.append(" and biz_type=").append("'").append(labelV).append("'");
            log.info(" and biz_type='" + labelV + "'");
        }
        //5,payType,eg:0,1,暂时不支持查询
                 /*if (labelK.equals("payType")) {
                    filterA.append(" and payType").append("=").append(labelV);
				}*/
        //6,packageId,eg:
        if (labelK.equals("packageId")) {
            filterA.append(" and package_type").append("=").append(labelV);
            log.info(" and package_type=" + labelV);
        }
        //7,province,eg:省份id如下
        // 030 ,088 ,087 ,013 ,031 ,075 ,081 ,034 ,019 ,097 ,071 ,011 ,076
        // 051 ,089 ,086 ,038 ,090 ,070 ,018 ,059 ,010 ,036 ,050 ,079 ,083
        // 017 ,085 ,084 ,074 ,091
        if (labelK.equals("province")) {
            String province = labelV.replace(",", "','");
            filterA.append(" and province_id in ('").append(province).append("')");
            log.info(" and province_id in ('" + province + "')");
        }
    }

    /**
     * 根据标签条件解析符合sparkSQL的查询过滤解析，
     * 该SQL最终查询hive表t_mrt_zhfw_user_mon
     *
     * @param partition 使用频率表的分区
     * @param frequency 频次标签数据的value
     * @return 根据分区和标签条件获取where过滤分句
     */
    public static String parseUseFrequency(String partition, String frequency) {
        String monthArea = frequency.split(",")[0];
        String timesArea = frequency.split(",")[1];

        StringBuffer sb = new StringBuffer(" where application='" + partition + "'");
        int monBegin = 1;
        int monEnd = 12;
        String monthAreaBegin = getSplit(monthArea, "-", 0);
        String monthAreaEnd = getSplit(monthArea, "-", 1);
        if (!monthAreaBegin.equals("")) {
            monBegin = Integer.valueOf(monthAreaBegin);
        }
        if (!monthAreaEnd.equals("")) {
            monEnd = Integer.valueOf(monthAreaEnd);
        }

        String timesAreaBegin = getSplit(timesArea, "-", 0);
        String timesAreaEnd = getSplit(timesArea, "-", 1);

        for (int i = monBegin; i <= monEnd; i++) {
            if (i < 10) {
                if (!timesAreaBegin.equals("")) {
                    sb.append(" and mon_0").append(i).append(">=").append(timesAreaBegin);
                    log.info(" and mon_0" + i + ">=" + timesAreaBegin);
                }
                if (!timesAreaEnd.equals("")) {
                    sb.append(" and mon_0").append(i).append("<=").append(timesAreaEnd);
                    log.info(" and mon_0" + i + "<=" + timesAreaEnd);
                }
            } else {
                sb.append(" and mon_").append(i);

                if (!timesAreaBegin.equals("")) {
                    sb.append(" and mon_").append(i).append(">=").append(timesAreaBegin);
                    log.info(" and mon_" + i + ">=" + timesAreaBegin);
                }
                if (!timesAreaEnd.equals("")) {
                    sb.append(" and mon_").append(i).append("<=").append(timesAreaEnd);
                    log.info(" and mon_" + i + "<=" + timesAreaEnd);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取当前日期按照月份加减后的日期
     *
     * @param addMonth 按照月份加减的月数
     * @return 8为数字组成的时间格式
     */
    public static String getTime(int addMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, addMonth);
        return formatter.format(c.getTime());
    }

    /**
     * 指定分隔符分割指定的字符串,根据mark(0/1)获取对应的切分子串
     * 如果分隔符在字符串中不存在,或者在字符串首部或尾部,相应的子串均以""返回
     *
     * @param str   要切分的字符串
     * @param split 分隔符
     * @param mark  分割后的两个子串的标记,0表示第一个,1表示第二个
     * @return 分割后mark对应字串
     */
    public static String getSplit(String str, String split, int mark) {
        int l = str.trim().length();
        // -1,0,l
        int s = str.indexOf(split);
        if (mark == 0 && 0 <= s && s < l) {
            return str.substring(0, s);
        } else if (mark == 1 && 0 <= s && s < l) {
            return str.substring(s + 1, l);
        }
        return "";
    }
}
