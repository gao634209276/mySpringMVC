package spring.hessian.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Parse {
	private static Log log = LogFactory.getLog(Parse.class);




	public static String parseSQL(HashMap<String, String> label) {
		List<String> colB = new ArrayList<>(Arrays.asList("province", "netType","payType","packageId","age","netTime","netAge"));
		List<String> colA = new ArrayList<>(Arrays.asList("netUseFrequ", "mobUseFrequ"));

        StringBuilder filterA = new StringBuilder("");
        StringBuilder filterBweb = new StringBuilder("");
        StringBuilder filterBmob = new StringBuilder("");
		/*
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
            /*
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
            if (colA.contains(key)) {
                parseBaseInfo(key.trim(), label.get(key).trim(), filterA);
            }
             /*
             * table:t_mrt_zhfw_user_mon
             * mobile,province,city,mon_01-mob_12,application
             * partition:
             * application=mob
             * application=sms
             * application=web
             */
            else if (colB.contains(key)) {
                // 1,netUseFrequ
                if (key.trim().equals("netUseFrequ")) {
                    filterBweb.append("select mobile from t_mrt_zhfw_user_mon ")
                            .append(parseUseFrequency("web", label.get(key).trim()));
                }
                // 2,mobUseFrequ
                if (key.trim().equals("mobUseFrequ")) {
                    filterBmob.append("select mobile from t_mrt_zhfw_user_mon ")
                            .append(parseUseFrequency("mob", label.get(key).trim()));
                }

            } else {
                log.error("not match label : " + key);
            }

        }

        String sqlBweb = filterBweb.toString();
        String sqlBmob = filterBmob.toString();
        String sqlA = "";
        String sqlB = "";
        StringBuilder sql = new StringBuilder();

        if(filterA.toString().length()>0){
            sqlA= "select number as mobile from t_bigdata_user_mon where 1=1 "+filterA.toString();
        }
        if(sqlBweb.length()>0 && sqlBmob.length()>0){
            sqlB = sqlBweb+" intersect "+sqlBmob;
        }else if(sqlBweb.length()>0){
            sqlB = sqlBweb;
        }else{
            sqlB = sqlBmob;
        }

        if (sqlA.length()>0 && sqlB.length()>0){
            sql.append("select mobile from (").append(sqlA).append(") a ")
                    .append(" join (").append(sqlB).append(")b")
                    .append(" on a.mobile = b.mobile");
        }else if (sqlB.length()>0){
            sql.append("select mobile from (").append(sqlB).append(")");
        }else{
            sql.append(sqlA);
        }
		return sql.toString();
	}


    public static String getTime(int addMonth) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, addMonth);
        return formatter.format(c);
    }

    public static void parseBaseInfo(String labelK,String labelV,StringBuilder filterA){
        // 1.age,eg:0-30
        if (labelK.equals("age")) {
            // 18:select case when(length(card_id)=18 then months_between(current_date,concat(substr(card_id,7,4),'-',substr(card_id,11,2),'-',substr(card_id,13,2))) as age,
            // 15:select months_between(current_date,concat('19',substr(card_id,7,2),'-',substr(card_id,9,2),'-',substr(card_id,11,2))) as age,
					/*
					 * select number,case when(length(card_id)=18 then
					 * months_between(current_date,
					 * concat(substr(card_id,7,4),'-',substr(card_id,11,2),'-',substr(card_id,13,2))
					 * ) else if length(card_id)=15 then
					 * months_between(current_date,
					 * concat('19',substr(card_id,7,2),'-',substr(card_id,9,2),'-',substr(card_id,11,2))
					 * ) else 0 end
					 * )
					 * as age
					 * from t_bigdata_user_mon;
					 */
					/*String ageSql = "select " +
							"case length(card_id) when 18 then " +
							"months_between(current_date, " +
							"concat(substr(card_id,7,4),'-',substr(card_id,11,2),'-',substr(card_id,13,2))" +
							") when 15 then" +
							"months_between(current_date," +
							"concat('19',substr(card_id,7,2),'-',substr(card_id,9,2),'-',substr(card_id,11,2))" +
							") else 0 end " +
							"as age from t_bigdata_user_mon";*/

            String getAge = " and " +
                    "case length(card_id) when 18 then substr(card_id,7,8)" +
                    "when 15 then concat('19',substr(card_id,7,6))" +
                    "else null end ";
            String[] ageArea = labelV.split("-");
            if (!ageArea[0].equals("")) {
                String birthdayBegin = getTime(0 - Integer.valueOf(ageArea[0].trim()));
                filterA.append(getAge).append(">=").append(birthdayBegin);
            }
            if (!ageArea[1].equals("")) {
                String birthdayEnd = getTime(0 - Integer.valueOf(ageArea[1].trim()));
                filterA.append(getAge).append("<=").append(birthdayEnd);
            }
        }


        //2,frist_time,eg: 20110202-20150505:
        if (labelK.equals("netTime")) {
            String[] netTimeArea = labelV.split("-");
            if (!netTimeArea[0].equals("")) {
                filterA.append(" and frist_time>=").append(netTimeArea[0]);
            }
            if (!netTimeArea[1].equals("")) {
                filterA.append(" and frist_time<=").append(netTimeArea[1]);
            }
        }

        //3,netAge,eg: 5-10
        if (labelK.equals("netAge")) {
            int netAge = Integer.valueOf(labelV);
            String[] netAgeArea = labelV.split("-");
            if (!netAgeArea[0].equals("")) {
                String netAgeTimeEnd = getTime(0 - Integer.valueOf(netAgeArea[0].trim()));
                filterA.append(" and frist_time<=").append(netAgeTimeEnd);
            }
            if (!netAgeArea[1].equals("")) {
                String netAgeTimeBegin = getTime(0 - Integer.valueOf(netAgeArea[1].trim()));
                filterA.append(" and frist_time>=").append(netAgeTimeBegin);
            }
        }

        //4,netType,eg:2G
        if (labelK.equals("netType")) {
            //biz_type
            filterA.append(" and biz_type=").append(labelV);
        }
        //5,payType,eg:0
			 	/*if (labelK.equals("payType")) {
					filterA.append(" and payType").append("=").append(labelV);
				}*/
        //6,packageId,eg:
        if (labelK.equals("packageId")) {
            filterA.append(" and package_type").append("=").append(labelV);
        }
        //7,province,eg:
        // 030 ,088 ,087 ,013 ,031 ,075 ,081 ,034 ,019 ,097 ,071 ,011 ,076
        // 051 ,089 ,086 ,038 ,090 ,070 ,018 ,059 ,010 ,036 ,050 ,079 ,083
        // 017 ,085 ,084 ,074 ,091
        if (labelK.equals("province")) {
            filterA.append(" and province_id in (").append(labelV).append(")");
        }
    }

    public static String parseUseFrequency(String partition, String frequency) {

        String[] timesArea = frequency.split(",")[1].split("-");
        String[] monthArea = frequency.split(",")[0].split("-");

        StringBuilder sb = new StringBuilder(" where application='" + partition + "'");
        int monthAreaBegin = 1;
        int monthAreaEnd = 12;
        if (!monthArea[0].equals("")) {
            monthAreaBegin = Integer.valueOf(monthArea[0]);
        }
        if (!monthArea[1].equals("")) {
            monthAreaEnd = Integer.valueOf(monthArea[1]);
        }
        for (int i = monthAreaBegin; i <= monthAreaEnd; i++) {
            if (i < 10) {
                if (!timesArea[0].equals("")) {
                    sb.append(" and mon_0").append(i).append(">=").append(timesArea[0]);
                }
                if (!timesArea[1].equals("")) {
                    sb.append(" and mon_0").append(i).append("<=").append(timesArea[1]);
                }
            } else {
                if (!timesArea[0].equals("")) {
                    sb.append(" and mon_").append(i).append(">=").append(timesArea[0]);
                }
                if (!timesArea[1].equals("")) {
                    sb.append(" and mon_").append(i).append("<=").append(timesArea[1]);
                }
            }
        }

        return sb.toString();
    }
}
