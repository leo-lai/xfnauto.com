package main.com.weixin.service;

import java.util.Date;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.dao.search.WeixinAppTokenSearch;
import main.com.weixin.dao.vo.WeixinAppTokenVo;

public interface WeixinAppTokenService extends BaseService<WeixinAppToken>{

	//获取来自关注的授权信息
	public WeixinAppToken getAccessTokenJAVA()throws Exception;
	
	public WeixinAppToken getAccessTokenApp()throws Exception;
	
	/**
	 * 获取通联的APPID资料
	 * @return
	 * @throws Exception
	 */
	public WeixinAppTokenVo getAccessTokenAllInPay()throws Exception;

	/**
	 * 获取网页授权Token(刷新)
	 * @return
	 * @throws Exception
	 */
	public WeixinAppToken getAccessTokenWEBByRefresh()throws Exception;

	/**
	 * 利用code获取网页授权token
	 * @param code
	 * @return
	 */
	public WeixinAppToken setAccessTokenWEB(String code)throws Exception;

	/**
	 * 获取JsapiTicket授权
	 * @return
	 */
	public Result getJsapiTicket(String url)throws Exception;
	/**
	 * 获取JsapiTicket授权
	 * @return
	 */
	public Result getJsapiTicketApp(String url)throws Exception;
	/**
	 * 获取GPS授权
	 * @return
	 */
	public WeixinAppToken getGPSApp()throws Exception;
	/**
	 * 百度授权
	 * @return
	 */
	public WeixinAppToken getBaiduApp()throws Exception;
	
	/**
	 * 消息推送
	 * @param openId
	 * @param agentType
	 * @param isBinding
	 * @param name
	 * @param phoneNumberOrAmount
	 * @param rebateType
	 * @param backAmount
	 * @return
	 * @throws Exception
	 */
	public Boolean pushTemplate(String openId,int agentType,Boolean isBinding,String name,
			String phoneNumberOrAmount,String rebateType,String backAmountOrFristRemark,Boolean isSuccess);

	/**
	 * 生成微信公总号码
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public String getQRticket(String userCode)throws Exception;
}
