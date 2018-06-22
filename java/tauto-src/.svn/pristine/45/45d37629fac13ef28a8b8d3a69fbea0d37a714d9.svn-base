package main.com.weixinApp.dao.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ConsumerOrderUserVO implements Serializable {

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 0
     */
    private Integer isDel;
    
	private List<ConsumerOrderInfoVO> infos;
}
