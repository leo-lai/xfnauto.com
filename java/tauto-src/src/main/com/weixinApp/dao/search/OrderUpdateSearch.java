package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class OrderUpdateSearch extends BaseSearch implements Serializable{
	

	private static final long serialVersionUID = -5169355568760252976L;

	private Integer id;
	
    /**
     * 门店ID
     */
    private Integer orgId;

    /**
     * 门店名称
     */
    private String orgName;

    /**
     * 联系人名称
     */
    private String orgLinker;

    /**
     * 联系人手机号
     */
    private String orgPhone;

    /**
     * 物流渠道 1：自提 2：其他
     */
    private Integer logisticsType;

    /**
     * 物流车牌号
     */
    private String logisticsPlateNumber;

    /**
     * 物流司机姓名
     */
    private String logisticsDriver;

    /**
     * 物流司机手机
     */
    private String logisticsDriverPhone;

    /**
     * 物流单号
     */
    private String logisticsOrderCode;
    
    /**
     * 运费
     */
    private Double freight;

    /**
     * 预计交车日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pickCarDate;

    /**
     * 交车地址
     */
    private String pickCarAddr;

}
