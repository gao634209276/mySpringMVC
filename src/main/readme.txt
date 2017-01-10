1,接收智慧服务发送订单中心标签项，返回接收成功
2,筛选数据
3,结果写入redis

标签数据：
	settingId:String,len=20
	label:String
		province:a,b,c
		netType:2G,3G,4G
		payType:0,1
		packageId:
		age:-50
		netTime:20160501-
		netAge:
		netUseFrequ:1-4,10-
		mobUseFrequ:4,
	source:String,1:独立完成,不需要其他渠道协助;2:需要其他渠道数据辅助完成
	filename:String,其他渠道的ftp文件path

应答参数：
	respcode:String,len=4,成功：0000
	respdesc:String,len=500

redis：sorted set，score递增顺序
	SMART_LABEL_ID_TMP-->SMART_LABEL_ID



