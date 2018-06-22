package main.com.weixinHtml.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShareMaterialInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer materialInfoId;

    /**
     * 
     */
    private String materialInfoName;

    /**
     * 
     */
    private String remarks;

    /**
     * 
     */
    private String image;

    /**
     * 上级素材ID
     */
    private Integer materialId;

    /**
     * 
     */
    private Integer systemUserId;

    /**
     * 是否已分享
     */
    private Boolean overShare;

    /**
     * 阅读量（不完全统计）
     */
    private Integer readName;

    /**
     * 
     */
    private Boolean overDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 
     * @return material_info_id 
     */
    public Integer getMaterialInfoId() {
        return materialInfoId;
    }

    /**
     * 
     * @param materialInfoId 
     */
    public void setMaterialInfoId(Integer materialInfoId) {
        this.materialInfoId = materialInfoId;
    }

    /**
     * 
     * @return material_info_name 
     */
    public String getMaterialInfoName() {
        return materialInfoName;
    }

    /**
     * 
     * @param materialInfoName 
     */
    public void setMaterialInfoName(String materialInfoName) {
        this.materialInfoName = materialInfoName;
    }

    /**
     * 
     * @return remarks 
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 
     * @param remarks 
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 
     * @return image 
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image 
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 上级素材ID
     * @return material_id 上级素材ID
     */
    public Integer getMaterialId() {
        return materialId;
    }

    /**
     * 上级素材ID
     * @param materialId 上级素材ID
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    /**
     * 
     * @return system_user_id 
     */
    public Integer getSystemUserId() {
        return systemUserId;
    }

    /**
     * 
     * @param systemUserId 
     */
    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    /**
     * 是否已分享
     * @return over_share 是否已分享
     */
    public Boolean getOverShare() {
        return overShare;
    }

    /**
     * 是否已分享
     * @param overShare 是否已分享
     */
    public void setOverShare(Boolean overShare) {
        this.overShare = overShare;
    }

    /**
     * 阅读量（不完全统计）
     * @return read_name 阅读量（不完全统计）
     */
    public Integer getReadName() {
        return readName;
    }

    /**
     * 阅读量（不完全统计）
     * @param readName 阅读量（不完全统计）
     */
    public void setReadName(Integer readName) {
        this.readName = readName;
    }

    /**
     * 
     * @return orver_delete 
     */
    
    public Boolean getOverDelete() {
        return overDelete;
    }

    /**
     * 
     * @param orverDelete 
     */
    public void setOverDelete(Boolean overDelete) {
        this.overDelete = overDelete;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}