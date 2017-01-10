package spring.hessian.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.jstl.IntegerDivideOperator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Parse {
	private static Log log = LogFactory.getLog(Parse.class);

	public static String getTime(int addMonth) {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, addMonth);
		return formatter.format(c);
	}

	public static String parsePartition(String partition, String frequence) {

		String[] timesArea = frequence.split(",")[1].split("-");
		String[] monthArea = frequence.split(",")[0].split("-");

		StringBuilder sb = new StringBuilder(" and application='" + partition + "'");
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


	public static String parseSQL(HashMap<String, String> label) {
		List<String> cola = new ArrayList<>(Arrays.asList("province", "b"));
		List<String> colb = new ArrayList<>(Arrays.asList("c", "d"));


		Iterator<String> it = label.keySet().iterator();
		StringBuilder sql = new StringBuilder("select mobile from ");

		StringBuilder filterA = new StringBuilder(" where 1=1 ");
		StringBuilder filterB = new StringBuilder(" where 1=1 ");
		/**
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
		while (it.hasNext()) {
			String key = it.next();

			/**
			 * table:t_mrt_zhfw_user_mon
			 * mobile,province,city,mon_01-mob_12,application
			 * partition:
			 * application=mob
			 * application=sms
			 * application=web
			 */
			if (cola.contains(key)) {
				// 1,netUseFrequ
				if (key.trim().equals("netUseFrequ")) {
					parsePartition("mob", label.get(key).trim());
					filterB.append(parsePartition("mob", label.get(key).trim()));
				}

				// 2,mobUseFrequ
				if (key.trim().equals("mobUseFrequ")) {

				}


			}
			/**
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
			else if (colb.contains(key)) {

				// 1.age,eg:0-30
				if (key.trim().equals("age")) {
					// 18:select case when(length(card_id)=18 then months_between(current_date,concat(substr(card_id,7,4),'-',substr(card_id,11,2),'-',substr(card_id,13,2))) as age,
					// 15:select months_between(current_date,concat('19',substr(card_id,7,2),'-',substr(card_id,9,2),'-',substr(card_id,11,2))) as age,
					/**
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
					String[] ageArea = label.get(key).trim().split("-");
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
				if (key.trim().equals("netTime")) {
					String[] netTimeArea = label.get(key).trim().split("-");
					if (!netTimeArea[0].equals("")) {
						filterA.append(" and frist_time>=").append(netTimeArea[0]);
					}
					if (!netTimeArea[1].equals("")) {
						filterA.append(" and frist_time<=").append(netTimeArea[1]);
					}
				}

				//3,netAge,eg: 5-10
				if (key.trim().equals("netAge")) {
					int netAge = Integer.valueOf(label.get(key));
					String[] netAgeArea = label.get(key).trim().split("-");
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
				if (key.trim().equals("netType")) {
					//biz_type
					filterA.append(" and biz_type=").append(label.get(key));
				}
				//5,payType,eg:0
			 	/*if (key.trim().equals("payType")) {
					filterA.append(" and payType").append("=").append(label.get(key));
				}*/
				//6,packageId,eg:
				if (key.trim().equals("packageId")) {
					filterA.append(" and package_type").append("=").append(label.get(key));
				}
				//7,province,eg:
				// 030 ,088 ,087 ,013 ,031 ,075 ,081 ,034 ,019 ,097 ,071 ,011 ,076
				// 051 ,089 ,086 ,038 ,090 ,070 ,018 ,059 ,010 ,036 ,050 ,079 ,083
				// 017 ,085 ,084 ,074 ,091
				if (key.trim().equals("province")) {
					filterA.append(" and province_id in (").append(label.get(key)).append(")");
				}


			} else {
				log.error("not match label : " + it);
			}
		}

		return null;
	}
}
