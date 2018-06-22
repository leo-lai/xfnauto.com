package main.com.weixin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.utils.Base64Util;
import main.com.utils.GeneralConstant;
import main.com.utils.HTTPRequest;
import main.com.utils.StringUtil;
import main.com.utils.Value;
import main.com.utils.WeiXinQRDownLoad;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.message.Article;
import main.com.weixin.message.Image;
import main.com.weixin.message.ImageMessage;
import main.com.weixin.message.NewsMessage;
import main.com.weixin.service.WeixinAppTokenService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信公众号交互请求处理
 * @author Zwen
 *
 */
@Service
public class CoreService {
	@Autowired
	WeixinAppTokenService weixinAppTokenService;
	
	/**
     * 处理微信发来的请求
     * @param request
     * @return xml
     */
    public synchronized String processRequest(HttpServletRequest request,HttpServletResponse response) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "";
//        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
//            System.out.println("微信推送的信息："+requestMap);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName"); //发送方OpenId
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            
            String back = "";//回复内容

//             回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//             回复图片消息
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setToUserName(fromUserName);
            imageMessage.setFromUserName(toUserName);
            imageMessage.setCreateTime(new Date().getTime());
            imageMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
//             回复图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setArticleCount(2);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
            	back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
                back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
                back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
                back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
                back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
                back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "尊敬的喜蜂鸟客户，如果您有任何问题，请随时致电客服："+GeneralConstant.KEFU;
                back = MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                String ucCode = "";
                // 关注
                if (eventType.toUpperCase().equals(MessageUtil.EVENT_TYPE_SUBSCRIBE.toUpperCase())) {
                	//写入用户OpendId
                	if(!requestMap.containsKey("EventKey") || StringUtil.isEmpty(requestMap.get("EventKey")+"")){//如果是直接关注
//                    	shopUsersService.checkUserOpenId(fromUserName,true);
                	}else if(requestMap.get("EventKey").indexOf("qrscene")>-1){//如果是扫码关注qrscene_

                		}else{
                		}
                	}else{
                	}
                	newsMessage = getNewMessage(newsMessage, ucCode);
                	back = MessageUtil.REQ_MESSAGE_TYPE_EVENT;
            }
                // 取消关注
                else if (msgType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (msgType.toUpperCase().equals(MessageUtil.EVENT_TYPE_SCAN.toUpperCase())) {
                    // TODO 处理扫描带参数二维码事件
                	if(requestMap.containsKey("EventKey") || StringUtil.isNotEmpty(requestMap.get("EventKey")+"")){
                		String eventKey = requestMap.get("EventKey");
                		//去除前缀
                		eventKey = eventKey.replaceAll("scene_", "");
                		String regEx="[^0-9]";   
            			Pattern p = Pattern.compile(regEx);
            			Matcher m = p.matcher(eventKey);
                	}
                	newsMessage = getNewMessage(newsMessage, "");
                	back = MessageUtil.REQ_MESSAGE_TYPE_EVENT;
                	
                }
                // 上报地理位置
                else if (msgType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (msgType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                	String eventKey = requestMap.get("EventKey");
                	if("QR".equals(eventKey)){//我的二维码菜单被点击
                        	}else{
                        		//直接回复
                        		Image image = new Image();
                        		imageMessage.setImage(image);
                        		back = MessageUtil.REQ_MESSAGE_TYPE_IMAGE;
                        	}
                }
            // 设置文本消息的内容(现在系统只支持两种回复，文字和图文)
            if(back.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){//回复点击菜单事件
                // 将图片消息对象转换成xml
                respXml = MessageUtil.messageToXml(imageMessage);
            }else if(back.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
            	textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }else{
            	 // 将图文消息对象转换成xml
                respXml = MessageUtil.messageToXml(newsMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
    
    //封装图文消息
    public NewsMessage getNewMessage(NewsMessage newsMessage,String ucCode){
//    	Article article = new Article();
//    	article.setTitle("电影");
//    	article.setPicUrl("http://opii7iyzy.bkt.clouddn.com/ssss.png");
//    	article.setUrl("http://mp.weixin.qq.com/s/7ROdYIFbPCkCM7Vwo1M1yQ");//带参数的点击链接（合伙人的推荐码）
//    	article.setDescription("电影");
    	
    	Article article = new Article();
    	article.setTitle("欢迎来到喜蜂鸟");
//    	article.setPicUrl("http://opii7iyzy.bkt.clouddn.com/penpen.png");
//    	article.setUrl(Value.Shop);//带参数的点击链接（合伙人的推荐码）
    	article.setDescription("欢迎来到喜蜂鸟");
    	
//    	Article article1 = new Article();
//    	article1.setTitle("限量抢购，9.9包邮到家");
//    	article1.setPicUrl("http://opii7iyzy.bkt.clouddn.com/mall/tuisong/tuisong.jpg");
//    	article1.setUrl(Value.goosInfo3);//
//    	article1.setDescription("限量抢购，9.9包邮到家");
    	
//    	Article article2 = new Article();
//    	article2.setTitle("欢迎来到喜蜂鸟");
//    	article2.setPicUrl("http://opii7iyzy.bkt.clouddn.com/zhaoshang.png");
//    	article2.setUrl(Value.Apply+ucCode);//带参数的点击链接
//    	article2.setDescription("欢迎来到喜蜂鸟");
    	List<Article> articles = new ArrayList<Article>();
    	articles.add(article);
//    	articles.add(article1);
//    	articles.add(article2);
    	newsMessage.setArticles(articles);
    	return newsMessage;
    }
}
