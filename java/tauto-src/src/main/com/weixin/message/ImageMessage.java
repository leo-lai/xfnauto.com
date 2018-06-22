package main.com.weixin.message;

import main.com.weixin.base.BaseMessage;

public class ImageMessage extends BaseMessage{
	 // 图片链接
//    private String PicUrl;
	
//    private String MediaId;
	
	  private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		this.Image = image;
	}

//    public String getPicUrl() {
//        return PicUrl;
//    }
//
//    public void setPicUrl(String picUrl) {
//        PicUrl = picUrl;
//    }

//    public String getMediaId() {
//        return MediaId;
//    }
//
//    public void setMediaId(String mediaId) {
//        MediaId = mediaId;
//    }
	  
}
