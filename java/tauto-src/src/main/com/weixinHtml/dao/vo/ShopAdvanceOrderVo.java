package main.com.weixinHtml.dao.vo;

import java.util.List;

import lombok.Data;
import main.com.system.dao.vo.OrganizationVo;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;

@Data
public class ShopAdvanceOrderVo extends ShopAdvanceOrder{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ShopAdvanceOrderPaymentVo> orderPaymentVos;
	
	List<ShopAdvanceOrderInfoVo> orderInfoVos; 
	
	OrganizationVo organizationVo;
	
	ShopAdvanceOrderPaymentVo orderPaymentVo;
	
	private String orgLinker;
	private String orgPhone;
	private String idCardPicOn;
	private String idCardPicOff;
	
	public ShopAdvanceOrderPaymentVo getOrderPaymentVo() {
		if(orderPaymentVos == null || orderPaymentVos.size() <= 0) {
			return null;
		}
		ShopAdvanceOrderPaymentVo orderPayment = null;
		for(ShopAdvanceOrderPaymentVo paymentVo : orderPaymentVos) {
			if(paymentVo.getOrderInPayState().equals(1)) {
				orderPayment = paymentVo;
				break;
			}
		}
		return orderPayment;
	}
}
