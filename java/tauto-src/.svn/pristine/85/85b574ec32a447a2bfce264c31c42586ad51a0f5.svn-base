package main.com.weixin.mune.controller;

import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.mune.Button;
import main.com.weixin.mune.CommonButton;
import main.com.weixin.mune.ComplexButton;
import main.com.weixin.mune.Menu;
import main.com.weixin.mune.TempButton;
import main.com.weixin.mune.WeixinUtil;
import main.com.weixin.service.WeixinAppTokenService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menuManager")
public class MenuManagerController {
	public static Logger logger = Logger.getLogger(MenuManagerController.class);


	@Autowired
	private WeixinAppTokenService appTokenService;
	
	/**
	 * 微信菜单控制类
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/create/mune", method = RequestMethod.POST)
	public Result createMune(String shopUrl,String myUrl,String partnerUrl) {
		Result result = new Result();
		try {
			WeixinAppToken appToken = appTokenService.getAccessTokenJAVA();
			int back = WeixinUtil.createMenu(getMenu(shopUrl,myUrl,partnerUrl), appToken.getAccessToken());
            // 判断菜单创建结果
            if (0 == back){
            	result.setOK(ResultCode.CODE_STATE_200, "微信菜单创建成功");
            	logger.info("菜单创建成功！");
            }else{
            	result.setError(ResultCode.CODE_STATE_4005, "微信菜单创建失败");
            	logger.info("菜单创建失败，错误码：" + result);
            }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	 /**
     * 组装菜单数据
     * 
     * button	是	一级菜单数组，个数应为1~3个
		sub_button	否	二级菜单数组，个数应为1~5个
		type	是	菜单的响应动作类型
		name	是	菜单标题，不超过16个字节，子菜单不超过40个字节
		key	click等点击类型必须	菜单KEY值，用于消息接口推送，不超过128字节
		url	view类型必须	网页链接，用户点击菜单可打开链接，不超过256字节
		media_id	media_id类型和view_limited类型必须	调用新增永久素材接口返回的合法media_id
     * @return
     */
    private static Menu getMenu(String shopUrl,String myUrl,String partnerUrl) {
    	
    	//菜单示例
//        CommonButton btn11 = new CommonButton();
//        btn11.setName("天气预报");
//        btn11.setType("click");
//        btn11.setKey("11");
//
//        CommonButton btn12 = new CommonButton();
//        btn12.setName("公交查询");
//        btn12.setType("click");
//        btn12.setKey("12");
//
//        CommonButton btn13 = new CommonButton();
//        btn13.setName("周边搜索");
//        btn13.setType("click");
//        btn13.setKey("13");
//
//        CommonButton btn14 = new CommonButton();
//        btn14.setName("历史上的今天");
//        btn14.setType("click");
//        btn14.setKey("14");
//
//        CommonButton btn21 = new CommonButton();
//        btn21.setName("歌曲点播");
//        btn21.setType("click");
//        btn21.setKey("21");
//
//        CommonButton btn22 = new CommonButton();
//        btn22.setName("经典游戏");
//        btn22.setType("click");
//        btn22.setKey("22");
//
//        CommonButton btn23 = new CommonButton();
//        btn23.setName("美女电台");
//        btn23.setType("click");
//        btn23.setKey("23");
//
//        CommonButton btn24 = new CommonButton();
//        btn24.setName("人脸识别");
//        btn24.setType("click");
//        btn24.setKey("24");
//
//        CommonButton btn25 = new CommonButton();
//        btn25.setName("聊天唠嗑");
//        btn25.setType("click");
//        btn25.setKey("25");
//
//        CommonButton btn31 = new CommonButton();
//        btn31.setName("Q友圈");
//        btn31.setType("click");
//        btn31.setKey("31");
//
//        CommonButton btn32 = new CommonButton();
//        btn32.setName("电影排行榜");
//        btn32.setType("click");
//        btn32.setKey("32");
//
//        CommonButton btn33 = new CommonButton();
//        btn33.setName("幽默笑话");
//        btn33.setType("click");
//        btn33.setKey("33");

        
        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */
        
//        ComplexButton mainBtn1 = new ComplexButton();
//        mainBtn1.setName("生活助手");
//        //一级下有4个子菜单
//        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });
//
//        
//        ComplexButton mainBtn2 = new ComplexButton();
//        mainBtn2.setName("休闲驿站");
//        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });
//
//        
//        ComplexButton mainBtn3 = new ComplexButton();
//        mainBtn3.setName("更多体验");
//        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
    	
    	//多级菜单

//        CommonButton btn12 = new CommonButton();
//        btn12.setName("公交查询");
//        btn12.setType("click");
//        btn12.setKey("12");
//
//        CommonButton btn13 = new CommonButton();
//        btn13.setName("周边搜索");
//        btn13.setType("click");
//        btn13.setKey("13");
//
//        CommonButton btn14 = new CommonButton();
//        btn14.setName("历史上的今天");
//        btn14.setType("click");
//        btn14.setKey("14");
//
//        CommonButton btn21 = new CommonButton();
//        btn21.setName("歌曲点播");
//        btn21.setType("click");
//        btn21.setKey("21");
//
//        CommonButton btn22 = new CommonButton();
//        btn22.setName("经典游戏");
//        btn22.setType("click");
//        btn22.setKey("22");
//
//        CommonButton btn23 = new CommonButton();
//        btn23.setName("美女电台");
//        btn23.setType("click");
//        btn23.setKey("23");
//
//        CommonButton btn24 = new CommonButton();
//        btn24.setName("人脸识别");
//        btn24.setType("click");
//        btn24.setKey("24");
//
//        CommonButton btn25 = new CommonButton();
//        btn25.setName("聊天唠嗑");
//        btn25.setType("click");
//        btn25.setKey("25");
//
//        CommonButton btn31 = new CommonButton();
//        btn31.setName("Q友圈");
//        btn31.setType("click");
//        btn31.setKey("31");
//
//        CommonButton btn32 = new CommonButton();
//        btn32.setName("电影排行榜");
//        btn32.setType("click");
//        btn32.setKey("32");
//
//        CommonButton btn33 = new CommonButton();
//        btn33.setName("幽默笑话");
//        btn33.setType("click");
//        btn33.setKey("33");
    	
    	
    	/**
    	 * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
    	 */
    	
//        ComplexButton mainBtn1 = new ComplexButton();
//        mainBtn1.setName("生活助手");
//        //一级下有4个子菜单
//        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });
//
//        
//        ComplexButton mainBtn2 = new ComplexButton();
//        mainBtn2.setName("休闲驿站");
//        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });
//
//        
//        ComplexButton mainBtn3 = new ComplexButton();
//        mainBtn3.setName("更多体验");
//        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
	     
    	
    	//只有一级菜单
//	      TempButton tempButton1 = new TempButton();
//	      tempButton1.setName("喷喷商城");
//	      tempButton1.setType("view");
//	      tempButton1.setUrl(shopUrl);
//	      
//	      TempButton tempButton2 = new TempButton();
//	      tempButton2.setName("个人中心");
//	      tempButton2.setType("view");
//	      tempButton2.setUrl(myUrl);
//	      
//	      TempButton tempButton3 = new TempButton();
//	      tempButton3.setName("合伙人");
//	      tempButton3.setType("view");
//	      tempButton3.setUrl(partnerUrl);
    	//多级可点击菜单
//	      TempButton tempButton1 = new TempButton();
//	      tempButton1.setName("在线客服");
//	      tempButton1.setType("view");
//	      tempButton1.setUrl("http://p.qiao.baidu.com/cps/chat?siteId=10424067&userId=23235048");//测试/正式
	      TempButton tempButton1 = new TempButton();
	      tempButton1.setName("U视商城");
	      tempButton1.setType("view");
//	      tempButton2.setUrl("http://h5.usee1.com.cn");//测试
	      tempButton1.setUrl("https://h5.ushiyihao.com");//正式
	      
	      TempButton tempButton2 = new TempButton();
	      tempButton2.setName("U视门诊");
	      tempButton2.setType("view");
//	      tempButton2.setUrl("http://h5.usee1.com.cn");//测试
	      tempButton2.setUrl("https://h5.ushiyihao.com/check/eye");//正式
	      
//	      TempButton tempButton31 = new TempButton();
//	      tempButton31.setName("申请代理");
//	      tempButton31.setType("view");
//	      tempButton31.setUrl("https://www.baidu.com/");
	      TempButton tempButton32 = new TempButton();
	      tempButton32.setName("小U店长");
	      tempButton32.setType("view");
//	      tempButton32.setUrl("http://h5.usee1.com.cn/me/xiaou");//测试
	      tempButton32.setUrl("https://h5.ushiyihao.com/me/xiaou");//正式
	      TempButton tempButton33 = new TempButton();
	      tempButton33.setName("合伙人");
	      tempButton33.setType("view");
//	      tempButton33.setUrl("http://hehuo.usee1.com.cn");//测试
	      tempButton33.setUrl("https://hehuo.ushiyihao.com");//正式
        CommonButton btn12 = new CommonButton();
        btn12.setName("我的二维码");
        btn12.setType("click");
        btn12.setKey("QR");
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("代理商");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new Button[] {tempButton32, tempButton33,btn12});
        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { tempButton1, tempButton2 ,mainBtn1 });
        return menu;
    }
}
