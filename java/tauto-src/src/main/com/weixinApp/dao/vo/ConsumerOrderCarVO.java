package main.com.weixinApp.dao.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import main.com.stock.dao.vo.StockCarVo;
@Data
public class ConsumerOrderCarVO implements Serializable{

	/**
     * ID
     */
    private Integer id;

    /**
     * 
     */
    private Integer infoId;
    private Integer stockCarId;
    /**
     * 车架号
     */
    private String vin;

    /**
     * 车大类ID
     */
    private Integer carsId;

    /**
     * 车大类名称
     */
    private String carsName;

    /**
     * 品牌ID
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
     * 颜色ID
     */
    private Integer colorId;

    /**
     * 颜色名称
     */
    private String colorName;

    /**
     * 内饰ID
     */
    private Integer interiorId;

    /**
     * 内饰名称
     */
    private String interiorName;

    /**
     * 审核状态 1：待审核 2：审核不通过（待修改价格） 3：审核不通过（已修改价格） 4：已审核
     */
    private Integer auditState;

    /**
     * 审核备注
     */
    private String auditRemark;

    /**
     * 验车照片
     */
    private String checkCarPic;

    /**
     * 票证
     */
    private String ticketPic;
    
    private String ticketRemark;
    
    private String certificationPic;
    
    private String tciPic;
    
    private String ciPic;
    
    private String expressPic;
    
    private String otherPic;
    
    private StockCarVo stockCar;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 
     */
    private Integer isDel;
}
