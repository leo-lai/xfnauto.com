package main.com.system.dao.vo;

/**
 * 作为物流返回解析数据转型用
 * @author User
 *
 */
public class ExpressBackChange {
	private String context;      //":"上海分拨中心/装件入车扫描 ", /*内容*/
	private String time;      //":"2012-08-28 16:33:19",           /*时间，原始格式*/
	private String ftime;      //":"2012-08-28 16:33:19",         /*格式化后时间*/
	private String status;      //":"在途",	       /*本数据元对应的签收状态。只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现*/
	private String areaCode;      //":"310000000000", /*本数据元对应的行政区域的编码，只有在开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现*/
	private String areaName;      //":"上海市",       /*本数据元对应的行政区域的名称，开通签收状态服务（见上面"status"后的说明）且在订阅接口中提交resultv2标记后才会出现*/
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
