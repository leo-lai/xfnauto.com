package main.com.utils.rlycode;

import java.text.SimpleDateFormat;

/**
 * 容联云实时通信工具类
 * @author qbs
 *
 */
public class RLYIMUtils {
	//正式
//	private static final String BaseUrl="https://app.cloopen.com:8883/2013-12-26";
	//测试
	private static final String BaseUrl="https://sandboxapp.cloopen.com:8883/2013-12-26";
	//////////////////主账号信息
	//主帐号Id
	private static final String ACCOUNT_SID="8a48b5514a27bb6c014a31f10cee0629AUTH";
	//主帐号授权令牌
	private static final String AUTH_TOKEN="dc2fb143a3c74a7ebf11f2ece2ca05beRest";
	//生产
	private static final String Rest_URL="https://app.cloopen.com:8883";
	//默认
	private static final String AppID="8a48b5514ee36774014ef7cee46618a7";
	////////////////子账号信息
	//子账号id
	private static final String CHILD_ACCOUNT_SID="cecdf91223e411e6bb9bac853d9f54f2";
	//子账号密码
	private static final String CHILD_ACCOUNT_PWD="cc7a82f701c5154db158d0396cc048e7";
	//VoIP账号
	private static final String VoIP_SID="8013924700000002";
	//VoIP账号密码
	private static final String VoIP_PWD="NPDuqrPC";
	
	public static SimpleDateFormat formatToSystem = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat formatFromRLY = new SimpleDateFormat("yyyyMMddhhmmss");
	
}
