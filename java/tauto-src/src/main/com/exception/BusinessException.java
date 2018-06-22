package main.com.exception;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月17日 下午2:28:16 
* 类描述： 业务异常
*/
public class BusinessException extends RuntimeException{
	
	/**
	 * 错误码
	 */
	private Integer errorCode;
	
	/**
	 * 错误消息
	 */
	private String errorMsg;
	
	public BusinessException(Integer errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	

}
