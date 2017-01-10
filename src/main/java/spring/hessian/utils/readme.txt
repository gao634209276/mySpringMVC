	create table as
	insert overwrite table as select
	insert overwrite local directory select
	load data local inpath
	alter table change column
	alter table add columns
	buckets
	join/group by不能决定reduce数量，通过spark.sql.shuffle.partitions=200/executor core
支持：
	alter table rename to
	alter table test set location/tblpropertoes(comment/external)
	alter table test set serdeproperties('field.delim'='\t');
	create table like
	truncate table
	drop table
	insert into table [partition] select ;
	hive中所有set参数，spark中所有set参数
	set spark.sql.thriftserver.scheduler.pool
sparkSQL支持hive不支持的sql：
	union all(不去重）
	union(去重并集）
	intersect（交集）
	except（除去右表）
	select from where in (select );

table:t_mrt_zhfw_user_mon
	mobile,province,city,mon_01-mob_12,application
	partition:
	application=mob
	application=sms
	application=web

table:t_bigdata_user_mon

date         月份
province_id  省份
number       电话号码
arpu         arpu值
user_name    客户姓名
card_id      证件号码
biz_type     业务类型
package_type 主套餐名称
frist_time   入网时间
mon
partition:mon



get:
	province:a,b,c
	netType:2G,3G,4G
	payType:0,1
	packageId:
	age:-50
	netTime:20160501-
	netAge:

	netUseFrequ:1-4,10-
	mobUseFrequ:4,

current_date
current_timestamp
date
date_add
date_format
date_sub
datediff
day
dayofmonth
dayofyear
from_unixtime
from_utc_timestamp
hour
last_day
minute
month
months_between
next_day
now
second
timestamp
unix_timestamp
weekofyear
year




select distinct length(card_id) from t_bigdata_user_mon;
select * from (
select
case length(card_id) when 18 then
months_between(current_date,
concat(substr(card_id,7,4),'-',substr(card_id,11,2),'-',substr(card_id,13,2))
) when 15 then
months_between(current_date,
concat('19',substr(card_id,7,2),'-',substr(card_id,9,2),'-',substr(card_id,11,2))
) else 0 end
as age from t_bigdata_user_mon) where age = 0 limit 10;