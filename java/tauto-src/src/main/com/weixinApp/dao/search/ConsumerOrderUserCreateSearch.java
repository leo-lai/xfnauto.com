package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderUserCreateSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -648311445879728924L;

	/**
     * ID
     */
    private Integer id;

    /**
     * 订购单ID
     */
    private Integer orderId;

    /**
     * 客户姓名
     */
    private String userName;

    /**
     * 手机号
     */
    @Pattern(regexp = "^\\d{11}$",message = "手机号码格式错误")
    private String userPhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 身份证正面
     */
    private String idCardPicOn;

    /**
     * 身份证反面
     */
    private String idCardPicOff;

    /**
     * 人员类型： 1：客户 2：提车人
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

}
