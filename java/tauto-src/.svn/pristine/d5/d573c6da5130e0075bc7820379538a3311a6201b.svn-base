package main.com.logistics.dao.po;

import java.io.Serializable;
import java.util.Date;

import org.junit.platform.commons.annotation.Testable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class LogisticsGoodsCar implements Serializable{
	
	private static final long serialVersionUID = 5857756116597950781L;

	/**
     * 货物车主键
     */
    private Integer goodsCarId;

    /**
     * 
     */
    private String frameNumber;

    /**
     * 托运单号
     */
    private Integer consignmentId;

    /**
     * 托运单号
     */
    private String consignmentCode;

    /**
     * 配送单号
     */
    private Integer distributionId;

    /**
     * 配送单号
     */
    private String distributionCode;

    /**
     *  0初始 1已分配 2运输中 3已送达
     */
    private Integer goodsCarState;

    /**
     * 品牌
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 车系ID
     */
    private Integer familyId;

    /**
     * 车系名称
     */
    private String familyName;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

    /**
     * 创建日期
     */
    private Date createDate;
    
    /**
     * 装车图片
     */
    private String acceptImage;

    /**
     * 送到移交图片
     */
    private String deliverToImage;
    
    /**
     * 签收图片
     */
    private String signPic;
    
    /**
     * 签收图片
     */
    private String signName;
    
    private Integer carsId;
    private int colourId;
    private String carsName;
    private String colourName;
    
    private int interiorId;
    
    private String interiorName;
    private String followInformation;
}