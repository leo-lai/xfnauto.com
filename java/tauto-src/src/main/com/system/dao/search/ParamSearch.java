package main.com.system.dao.search;

public class ParamSearch {

	/**
	 // "status":"polling",	 监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时”status= abort ，对于stuatus=abort的状度，需要增加额外的处理逻辑，详见本节最后的说明 
	 // "billstatus":"got",	 包括got、sending、check三个状态，由于意义不大，已弃用，请忽略
	 * "message":"",		 监控状态相关消息，如:3天查询无记录，60天无变化
			"autoCheck":"1",    快递公司编码是否出错，0为本推送信息对应的是贵司提交的原始快递公司编码（即2.1中的company字段，下同），1为本推送信息对应的是我方纠正后的新的快递公司编码。一个单如果我们连续3天都查不到结果，我方会（1）判断一次贵司提交的快递公司编码是否正确，如果正确，给贵司的回调接口（callbackurl）推送带有如下字段的信息：autoCheck=0、comOld与comNew都为空；（2）如果贵司提交的快递公司编码出错，我们会帮忙用正确的快递公司编码+原来的运单号重新提交订阅并开启监控（后续如果监控到单号有更新就给贵司的回调接口（callbackurl）推送带有如下字段的信息：autoCheck=1、comOld=原来的公司编码、comNew=新的公司编码）；并且给贵方的回调接口（callbackurl）推送一条含有如下字段的信息：status=abort、autoCheck=0、comOld为空、comNew=纠正后的快递公司编码。所以，如果判断到status=abort且comNew为空，则按见本节最后的关于status字段下【重要提醒】下的说明重新提交订阅；如查判断到status=abort且comNew不为空，则不需要重新提交订阅，且将贵司原来的快递公司编码改为comNew后的值，或在贵司数据库中增加一个快递公司编码为comNew+原来单号的运单；如果判断到status=polling且autoCheck=1，则此单为纠正公司编码后的跟踪信息，应保存。若开启了国际版（即在订阅请求中增加字段interCom=1），则回调请求中暂无此字段
		"comOld":"yuantong", 贵司提交的原始的快递公司编码。详细见autoCheck后说明。若开启了国际版（即在订阅请求中增加字段interCom=1），则回调请求中暂无此字段
		"comNew":"ems"，   我司纠正后的新的快递公司编码。详细见autoCheck后说明。若开启了国际版（即在订阅请求中增加字段interCom=1），则回调请求中暂无此字段
		"lastResult":{		  最新查询结果，若在订阅报文中通过interCom字段开通了国际版，则此lastResult表示出发国的查询结果，全量，倒序（即时间最新的在最前）
				"message":"ok",  消息体，请忽略
				"state":"0",     快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态，其中4-7需要另外开通才有效，详见章2.3.3 
				"status":"200",         通讯状态，请忽略
				"condition":"F00",		快递单明细状态标记，若state=0,则condition如下值代表如下状态：condition=CU001 等待清关condition=CU002 清关中condition=CU003 已清关condition=CU004 清关异常/
				"ischeck":"0",			是否签收标记，明细状态请参考2.3.3节state字段
				"com":"yuantong",       快递公司编码,一律用小写字母，见章2.5《快递公司编码》
				"nu":"V030344422",      单号
				"data":[
					{
		"context":"上海分拨中心/装件入车扫描 ", 内容
		"time":"2012-08-28 16:33:19",           时间，原始格式
		"ftime":"2012-08-28 16:33:19",         格式化后时间
		"status":"在途",	       本数据元对应的签收状态。只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		"areaCode":"310000000000", 本数据元对应的行政区域的编码，只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		"areaName":"上海市",       本数据元对应的行政区域的名称，开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		
		},{
		"context":"上海分拨中心/下车扫描 ",     内容
		"time":"2012-08-27 23:22:42",          时间，原始格式
		"ftime":"2012-08-27 23:22:42",        格式化后时间
		"status":"在途",			本数据元对应的签收状态。只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		"areaCode":"310000000000",  本数据元对应的行政区域的编码，只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		"areaName":"上海市",       本数据元对应的行政区域的名称，开通签收状态服务（见上面"status"后后的说明）且在订阅接口中提交resultv2标记后才会出现
		
		}
				]
			},
		
		"destResult":{              表示最新的目的国家的查询结果，只有在订阅报文中通过interCom=1字段开通了国际版才会显示此数据元，全量，倒序（即时间最新的在最前）
		"message":"ok",    消息体，请忽略
		"state":"0",       快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态，其中4-7需要另外开通才有效，详见章2.3.3 
		"status":"200",    通讯状态，请忽略
		"condition":"F00", 快递单明细状态标记，暂未实现，请忽略
		"ischeck":"0"     ,是否签收标记，明细状态请参考2.3.3节state字段
		"com":"speedpost",  快递公司编码,一律用小写字母，见章2.5《快递公司编码》
		"nu":"EX015142583SG", 单号
		"data":[
		{
		"context":"[01000]Final delivery Delivered to: SLOVESNOV",内容
		"time":"2016-05-24 14:00:00",    时间，原始格式
		"ftime":"2016-05-24 14:00:00",   格式化后时间
		"status":"签收"	本数据元对应的签收状态。只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		"areaCode":null, 本数据元对应的行政区域的编码，只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现
		"areaName":null,  本数据元对应的行政区域的名称，开通签收状态服务（见上面"status"后后的说明）且在订阅接口中提交resultv2标记后才会出现
		}
		]
		 }
**/
}
