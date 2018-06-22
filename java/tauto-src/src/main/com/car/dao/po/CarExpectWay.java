package main.com.car.dao.po;

import java.io.Serializable;

public class CarExpectWay implements Serializable {
    /**
     * 主键
     */
    private Integer expectWayId;

    /**
     * 购车方式名称
     */
    private String expectWayName;

    /**
     * 组织ID
     */
    private Integer orgId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织CODE
     */
    private String orgCode;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 主键
     * @return expect_way_id 主键
     */
    public Integer getExpectWayId() {
        return expectWayId;
    }

    /**
     * 主键
     * @param expectWayId 主键
     */
    public void setExpectWayId(Integer expectWayId) {
        this.expectWayId = expectWayId;
    }

    /**
     * 购车方式名称
     * @return expect_way_name 购车方式名称
     */
    public String getExpectWayName() {
        return expectWayName;
    }

    /**
     * 购车方式名称
     * @param expectWayName 购车方式名称
     */
    public void setExpectWayName(String expectWayName) {
        this.expectWayName = expectWayName;
    }

    /**
     * 组织ID
     * @return org_id 组织ID
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 组织ID
     * @param orgId 组织ID
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 组织名称
     * @return org_name 组织名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 组织名称
     * @param orgName 组织名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 组织CODE
     * @return org_code 组织CODE
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 组织CODE
     * @param orgCode 组织CODE
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 品牌ID
     * @return brand_id 品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 品牌ID
     * @param brandId 品牌ID
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}