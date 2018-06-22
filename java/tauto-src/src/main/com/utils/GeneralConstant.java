package main.com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 */
public interface GeneralConstant {
	
	public static final Integer QRwidth = 200;
	public static final Integer QRheight = 200;
	
	public static final String KEFU = "4001639989";
	
	public static final String EXPRESS = "ziti";
	
	public static final Integer userCodeLength = 6; //会员编号长度
	public static final Integer orgCodeLength = 6; //组织编码长度
	public static final Integer storageCodeLength = 4; //二级入库单后缀随机码长度

	
//	public static final Double RebateXIAOU = 25.0d;
//	public static final Double RebateHEHUOREN = 5.0d;
//	public static final Double RebateArea = 1.0d;
	
	public static final Double RebateXIAOU = 25.0d;
	public static final Double RebateHEHUOREN = 5.0d;
	public static final Double RebateArea = 1.0d;
	public static final Double RebateBinding = 1.0d;

	public static final int yantieMaxNumber = 10;

	
	/**
	 * 轮播图分组
	 * @author Zwen
	 *
	 */
	public abstract class Showgroup {
		/**
		 * 分组
		 *
		 */
		public static final String INDEX = "index";
		
		public static final String GOODS = "goods";
		
		public static final Integer ZERO = 0;

	}
	/**
	 * 系统真假
	 * @author Zwen
	 *
	 */
	public abstract class SystemBoolean {
		/**
		 * 分组
		 *
		 */
		public static final Boolean TRUE = true;
		
		public static final Boolean FALSE = false;

		public static final String SPLIT = ",";
		/**
		 * _
		 */
		public static final String SPLIT_ = "_";
		public static final String SPLIT_1 = "|";
		public static final String SPLIT_2 = "\\|";
	}
	/**
	 * 组织架构参数
	 * @author Zwen
	 *
	 */
	public abstract class Org {
		/**
		 * 第一级
		 */
		public static final Integer Level_1 = 1;
		/**
		 * 第二级
		 */
		public static final Integer Level_2 = 2;
		/**
		 * 第三级
		 */
		public static final Integer Level_3 = 3;
		/**
		 * 直营
		 */
		public static final Integer Type_1 = 1;
		/**
		 * 加盟
		 */
		public static final Integer Type_2 = 2;
		/**
		 * 联盟
		 */
		public static final Integer Type_3 = 3;
		/**
		 * 其他
		 */
		public static final Integer Type_4 = 4;
		/**
		 * 启用
		 */
		public static final Integer status_on = 1;
		/**
		 * 禁用
		 */
		public static final Integer status_off = 2;
		/**
		 * 待审核
		 */
		public static final Integer audited = 3;
		/**
		 * 门店
		 */
		public static final Integer store = 1;
		/**
		 * 4S点
		 */
		public static final Integer S4 = 2;
		/**
		 * 资源方
		 */
		public static final Integer resources = 3;
		
		//来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）
		/**
		 * HT（后台新建）
		 */
		public static final String HT = "HT";
		/**
		 * TG（推广）
		 */
		public static final String TG = "TG";
		/**
		 * SW（商务端新建）
		 */
		public static final String SW = "SW";
		/**
		 * YY（用户端预约）
		 */
		public static final String YY = "YY";
		/**
		 * WZ（其他）
		 */
		public static final String WZ = "WZ";
		/**
		 * SC(商城申请)
		 */
		public static final String SC = "SC";
	}
	
	/**
	 * 二级组织入库各种静态备注
	 * @author Zwen
	 *
	 */
	public abstract class StockStorageAbstract{
		/**
		 * 随车
		 */
		public static final int CertificateDate_one = 1;
		/**
		 * 3个工作日内
		 */
		public static final int CertificateDate_two = 2;
		/**
		 * 7个工作日内
		 */
		public static final int CertificateDate_three = 3;
		/**
		 * 10个工作日内
		 */
		public static final int CertificateDate_four = 4;
		/**
		 * 15个工作日内
		 */
		public static final int CertificateDate_five = 5;
	}
	/**
	 * 仓库类型
	 * @author Zwen
	 *
	 */
	public abstract class SystemWarehouseType {
		/**
		 * 整车型
		 */
		public static final Integer vehicle = 1; //整车型
		/**
		 * 配件型
		 */
		public static final Integer parts = 1; //配件型

	}
	
	public abstract class StockOrdersState {
		/**
		 * 下单已取消
		 */
		public static final Integer STATE_CANCEL = 0; //下单已取消
		/**
		 * 下单未付定金 1
		 */
		public static final Integer STATE_STATER = 1; //下单未定金
		/**
		 * 已付定金未通知 3 
		 */
//		public static final Integer STATE_INPAY = 3; //付定金未通知		
		/**
		 * 已通知未付尾款 5 notice
		 */
		public static final Integer STATE_NOTICE = 5; //已通知未付尾款 
		
		/**
		 * 付尾款没出库7storage
		 */
//		public static final Integer STATE_STORAGEOUT = 7; //付尾款 没出库
		
		/**
		 * 出库没收货 leave on 9
		 */
		public static final Integer STATE_LEAVEON = 9; 
		
		/**
		 * 签收并入库 11
		 */
		public static final Integer STATE_STORAGEPUTIN = 11; //
		
		/**
		 * 七天退货退款
		 */
		public static final Integer SEVEN = 7;
		
		/**
		 * 七天退货退款
		 */
		public static final Long SEVENTIME = 1000L*60*60*24*7;
		
		public static final Double depositPrice = 3000d;
		public static final Integer logistics_1  = 1;//随车
		public static final Integer logistics_2 = 2;//物流
		
		//ALL UNPAY RECEIVE EVALUATE
		public static final String STATE_ALL = "ALL";
		public static final String STATE_UNPAY = "UNPAY";
		public static final String STATE_RECEIVE = "RECEIVE";
		public static final String STATE_EVALUATE = "EVALUATE";
	}
	
	/**
	 * 各种订单前缀
	 * @author Zwen
	 *
	 */
	public abstract class OrderCodePRE {
		/**
		 * 订单号前缀
		 *
		 */
		public static final String ORDER_DD = "DD";
		/**
		 * 三级向二级订车单前缀
		 */
		public static final String ORDER_DC = "DC";
		/**
		 * 集中支付单号
		 */
		public static final String ORDER_PDD = "PD";
		/**
		 * 托运单支付
		 */
		public static final String ORDER_TY = "TY";
		/**
		 * 托运单单车支付
		 */
		public static final String ORDER_PT = "PT";
		/**
		 * 商城预定单支付
		 */
		public static final String ORDER_YY = "YY";
		/**
		 * 商城预定单支付
		 */
		public static final String ORDER_AY = "AY";
	}
	
	public abstract class CustomerOrderState {
		 /**
		 * 初始化
		 */
		public static final Integer initial  = 0;
		
		/**
		 * 开单
		 */
		public static final Integer start  = 1;		
		/**
		 * 等待银行审核
		 */
		public static final Integer paymentOfADeposit = 3;		
		/**
		 * 银行审核不通过
		 */
		public static final Integer notPassThrough = 4;		
		/**
		 *银行审核通过（非全款）等待车辆出库
		 */
		public static final Integer loanAudit = 5;		
//		/**
//		 * 车辆出库未交尾款
//		 */
//		public static final Integer theLibrary = 7;		
		/**
		 * 车辆已出库待加装精品
		 */
		public static final Integer deliveryOfTheTail = 7;		
		/**
		 * 加装精品完成待上牌
		 */
		public static final Integer retrofitting = 9;		
		/**
		 * 上牌完成待贴膜
		 */
		public static final Integer padPasting = 11;		
		/**
		 * 贴膜待交付车辆
		 */
		public static final Integer deliver = 13;		
		/**
		 * 已提车辆
		 */
		public static final Integer deliverTheLibrary = 15;
		/**
		 * 财务已确认交齐全款，整个订单已完成（已交车）
		 */
		public static final Integer orderBeenFinish = 17;
		/**
		 * 回访客户
		 */
		public static final Integer orderVisit = 19;
		
		/**
		 * 付款方式 1.全款
		 */
		public static final Integer fullPayment = 1;
		
		 /**
		  *  付款方式 2分期
		  */
		public static final Integer byStages = 2;
		/**
		 * 待银行审核
		 */
		
		public static final Integer initialization = 0;
		/**
		 * 审核通过 (不写在订单状态中，只在订单表中：auditStatus 银行审核字段中)
		 */
		public static final Integer passThrough = 1;
		
		/**
		 * 审核拒绝(不写在订单状态中，只在订单表中：auditStatus 银行审核字段中)
		 */
		public static final Integer refuse = 2;
	}
	
	public abstract class LoanBank{
		/**
		 * 奇瑞（金融机构）
		 */
		public static final int CHERY = 1;
		/**
		 * 奇瑞（金融机构）
		 */
		public static final String CHERY_NAME = "奇瑞";
		/**
		 * 瑞福德（金融机构）
		 */
		public static final int RUIFUDE = 2;
		/**
		 * 瑞福德（金融机构）
		 */
		public static final String RUIFUDE_NAME = "瑞福德";
		/**
		 * 建设银行
		 */
		public static final int JIANSHE = 3;
		/**
		 * 建设银行
		 */
		public static final String JIANSHE_NAME = "建设银行";
		/**
		 * 农业银行
		 */
		public static final int NONGYE = 4;
		/**
		 * 农业银行
		 */
		public static final String NONGYE_NAME = "农业银行";
		/**
		 * 工商银行行
		 */
		public static final int GONGSHANG = 5;
		/**
		 * 工商银行行
		 */
		public static final String GONGSHANG_NAME = "工商银行";
		/**
		 * 广州银行
		 */
		public static final int GUNAGZHOU = 6;
		/**
		 * 广州银行
		 */
		public static final String GUNAGZHOU_NAME = "广州银行";
		/**
		 * 鹤山珠江村镇银行
		 */
		public static final int HESHANZHUJIANG = 7;
		/**
		 * 鹤山珠江村镇银行
		 */
		public static final String HESHANZHUJIANG_NAME = "鹤山珠江村镇银行";
		/**
		 * 鹤山农村信用合作社
		 */
		public static final int HESHANNONGCUN = 8;
		/**
		 * 鹤山农村信用合作社
		 */
		public static final String HESHANNONGCUN_NAME = "鹤山农村信用合作社";
	}
	
	public abstract class PaymentMethod {
		public static final Integer WEIXINPAY = 1; //微信支付
	}

	/**
	 * 发票类型
	 * @author Zwen
	 *
	 */
	public abstract class PaperCheckType {
		/**
		 * negative 不开发票
		 *
		 */
		public static final Integer NEGATIVE  = 0; //返利中negative
		
		/**
		 * 普通发票
		 */
		public static final Integer ORDINARY = 1; //成功ordinary
		
		/**
		 * 增值税发票
		 */
		public static final Integer VALUEQDDEDTAX = 2; //成功ordinary
	}
	
	/**
	 * Ping++支付方式
	 * @author Zwen
	 *
	 */
	public abstract class PingPlusChannel {
		/**
		 * 支付宝 APP 支付
		 */
		public static final String unline = "pos";//	支付宝 APP 支付
		/**
		 * 支付宝 APP 支付
		 */
		public static final String alipay = "alipay";//	支付宝 APP 支付
		/**
		 * 支付宝手机网页支付
		 */
		public static final String alipay_wap = "alipay_wap";//	支付宝手机网页支付
		/**
		 * 支付宝电脑网站支付
		 */
		public static final String alipay_pc_direct = "alipay_pc_direct";//	支付宝电脑网站支付
		/**
		 * 支付宝当面付，即支付宝扫码支付
		 */
		public static final String alipay_qr = "alipay_qr";//	支付宝当面付，即支付宝扫码支付
		/**
		 * 百度钱包移动快捷支付，即百度钱包 APP 支付
		 */
		public static final String bfb = "bfb";//	百度钱包移动快捷支付，即百度钱包 APP 支付
		/**
		 * 百度钱包手机网页支付
		 */
		public static final String bfb_wap = "bfb_wap";//	百度钱包手机网页支付
		/**
		 * 银联企业网银支付，即 B2B 银联 PC 网页支付
		 */
		public static final String cp_b2b = "cp_b2b";//	银联企业网银支付，即 B2B 银联 PC 网页支付
		/**
		 * 银联支付，即银联 APP 支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）
		 */
		public static final String upacp = "upacp";//	银联支付，即银联 APP 支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）
		/**
		 * 银联手机网页支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）
		 */
		public static final String upacp_wap = "upacp_wap";//	银联手机网页支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）
		/**
		 * 银联网关支付，即银联 PC 网页支付
		 */
		public static final String upacp_pc = "upacp_pc";//	银联网关支付，即银联 PC 网页支付
		/**
		 * 微信 APP 支付
		 */
		public static final String wx = "wx";//	微信 APP 支付
		/**
		 * 微信公众号支付
		 */
		public static final String wx_pub = "wx_pub";//	微信公众号支付
		/**
		 * 微信公众号扫码支付
		 */
		public static final String wx_pub_qr = "wx_pub_qr";//	微信公众号扫码支付
		/**
		 * 微信 WAP 支付（此渠道仅针对特定客户开放）
		 */
		public static final String wx_wap = "wx_wap";//	微信 WAP 支付（此渠道仅针对特定客户开放）
		/**
		 * 微信小程序支付
		 */
		public static final String wx_lite = "wx_lite";//	微信小程序支付
		/**
		 * 易宝手机网页支付
		 */
		public static final String yeepay_wap = "yeepay_wap";//	易宝手机网页支付
		/**
		 * 京东手机网页支付
		 */
		public static final String jdpay_wap = "jdpay_wap";//	京东手机网页支付
		/**
		 * 分期乐支付
		 */
		public static final String fqlpay_wap = "fqlpay_wap";//	分期乐支付
		/**
		 * 量化派支付
		 */
		public static final String qgbc_wap = "qgbc_wap";//	量化派支付
		/**
		 * 招行一网通
		 */
		public static final String cmb_wallet = "cmb_wallet";//	招行一网通
		/**
		 * Apple Pay
		 */
		public static final String applepay_upacp = "applepay_upacp";//	Apple Pay
		/**
		 * 么么贷
		 */
		public static final String mmdpay_wap = "mmdpay_wap";//	么么贷
		/**
		 * 	QQ 钱包支付
		 */
		public static final String qpay = "qpay";//	QQ 钱包支付
		
		public static List<String> channel = new ArrayList<String>();
		{
			channel.add(unline);
			channel.add(alipay_qr);
			channel.add(wx_pub_qr);
		}
	}
	
	/**
	 * 支付方法
	 * @author Zwen
	 *
	 */
	public abstract class PayWay{
		/**
		 * 网关支付
		 */
		public static final Integer PAY_BY_WEB = 1;
		/**
		 * pos机
		 */
		public static final Integer PAY_BY_POS = 2;
		
		/**
		 * 自有POS机支付
		 */
		public static final Integer PAY_BY_POS_ONESELF = 3;
		
		/**
		 * 现金
		 */
		public static final Integer PAY_CASH = 4;
	}
	
	/**
	 * 支付方式
	 * 
	 *
	 */
	public abstract class PayModeType {
		/**
		 * 到店支付
		 */
		public static final int PAY_BY_SHOP = 1;
		/**
		 * 微信支付
		 */
		public static final int PAY_BY_WX = 2;
		/**
		 * 支付宝
		 */
		public static final int PAY_BY_ZFB = 3;
		/**
		 * 钱包
		 */
		public static final int PAY_WALLET = 4;
		/**
		 * POS机
		 */
		public static final int PAY_POS = 5;
		/**
		 * 现金
		 */
		public static final int PAY_CASH = 6;
		/**
		 * 挂账
		 */
		public static final int PAY_BILL = 7;
		/**
		 * 微信支付ping++方式
		 */
		public static final int PAY_BY_WX_PINGXX = 8;
		/**
		 * 通联支付
		 */
		public static final int PAY_ALLINPAY = 12;
		/**
		 * 通联支付
		 */
		public static final int PAY_ALLINPAY_WEIXIN_JS = 13;
		
		/**
		 * 自有POS机支付
		 */
		public static final Integer PAY_BY_POS_ONESELF = 14;
		
		public static Map<Integer, String> PayModeTypeMap = new HashMap<Integer, String>();
		static {
			PayModeTypeMap.put(PAY_BY_SHOP, "到店支付");
			PayModeTypeMap.put(PAY_BY_WX, "微信支付");
			PayModeTypeMap.put(PAY_BY_ZFB, "支付宝");
			PayModeTypeMap.put(PAY_WALLET, "钱包");
			PayModeTypeMap.put(PAY_POS, "POS机");
			PayModeTypeMap.put(PAY_CASH, "现金");
			PayModeTypeMap.put(PAY_BILL, "挂账");
			PayModeTypeMap.put(PAY_BY_WX_PINGXX, "微信支付ping++方式");
			PayModeTypeMap.put(PAY_ALLINPAY, "通联支付");
		}
	}
	/**
	 * 支付方式
	 * 
	 *
	 */
	public abstract class OrderInPayState {
		/**
		 * 未支付
		 */
		public static final Integer un_paid = 0;
		/**
		 * 已支付
		 */
		public static final Integer in_pay = 1;
		/**
		 * 已退款
		 */
//		public static final Integer refund = 2;
		/**
		 * 已退款
		 */
		public static final Integer refund = 3;
	}
	
	/**
	 * 配送板车类型
	 *
	 */
	public abstract class LogisticsCarType {
		/**
		 * 小型车
		 */
		public static final Integer MIN = 1;
		/**
		 * 中型车
		 */
		public static final Integer MID = 2;
		/**
		 * 大型车
		 */
		public static final Integer MAX = 3;
	}
	/**
	 * 配送单状态
	 *
	 */
	public abstract class DistributionState {
		/**
		 * 已取消 -1
		 */
		public static final Integer CANCEL = -1;
		/**
		 * 等待配送 0
		 */
		public static final Integer START = 0;
		/**
		 * 已派单
		 */
		public static final Integer ING = 1;
		/**
		 * 已接单
		 */
		public static final Integer DOME = 2;
		/**
		 * 已装车
		 */
		public static final Integer LOADED = 3;
		/**
		 * 已装车
		 */
		public static final Integer TRANSPORTING = 4; 
		/**
		 * 已到达目的地
		 */
		public static final Integer ARRIVED = 5;
		/**
		 * 已卸车
		 */
		public static final Integer UNLOADED = 6;
		/**
		 * 已签收
		 */
		public static final Integer SINGED = 7;
		/**
		 * 支付部分
		 */
		public static final Integer PARTOFPAY = 8;
		/**
		 * 支付全部
		 */
		public static final Integer OVERPAY = 9;
	}
	/**
	 * 配送单状态
	 *
	 */
	public abstract class GoodsCarsState {
		/**
		 * 已取消 -1
		 */
		public static final Integer CANCEL = -1;
		/**
		 * 等待分配0
		 */
		public static final Integer START = 0;
		/**
		 * 已配送等待装车 0
		 */
		public static final Integer TAKE = 1;
		/**
		 * 已装车配送中 1
		 */
		public static final Integer ING = 2;
		/**
		 * 卸车已完成 3
		 */
		public static final Integer DOME = 3;
		/**
		 * 已签收
		 */
		public static final Integer OVERSING = 4;
		/**
		 * 已支付
		 */
		public static final Integer OVERPAY = 5;
	}
	
	/**
	 * 托运状态
	 *
	 */
	public abstract class ConsignmentState {
		/**
		 * 已取消 -1
		 */
		public static final Integer CANCEL = -1;
		/**
		 * 等待分配0
		 */
		public static final Integer START = 0;
		/**
		 * 已配送等待装车 0
		 */
		public static final Integer TAKE = 1;
		/**
		 * 已装车配送中 1
		 */
		public static final Integer ING = 2;
		/**
		 * 卸车已完成 3
		 */
		public static final Integer DOME = 3;
		/**
		 * 支付部分
		 */
		public static final Integer PARTOFPAY = 4;
		/**
		 * 支付全部
		 */
		public static final Integer OVERPAY = 5;
		/**
		 * 专线配送
		 */
		public static final Integer TYPELINE = 2;
		/**
		 * 非专线配送（点对点配送）
		 */
		public static final Integer TYPEPOINT = 1;
	}    
}
