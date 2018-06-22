package main.com.frame.domain;

import java.io.Serializable;

import main.com.utils.GeneralConstant;
import main.com.utils.TakeCareMapDate;
import net.sf.json.JSONObject;

/**
 * 处理结果对象
 */
public class Result implements Serializable {
	
	private static final long serialVersionUID = 5905715228490291386L;

	private boolean success;
	/**
	 * @fields record 消息对象
	 */
	private Object message;

	private Object data;

	private int resultCode;

	public Result() {
		super();
	}
	

	public Result(Object data){
		this.success = true;
		this.resultCode = ResultCode.CODE_STATE_200;
		this.data = data;
		this.message = "操作成功";
	}
	
	public Result(int resultCode,boolean success,Object message) {
		this.success = success;
		this.resultCode = resultCode;
		this.message = message;
	}

	/**
	 * @description
	 * @param status
	 *            状态
	 * @param message
	 *            消息
	 */
	public Result(boolean success, Object message) {
		this.success = success;
		this.message = message;
		this.data = "";
	}

	/**
	 * 添加成功结果信息
	 * 
	 * @param record
	 */
	public void setOK(int resultCode, Object message, Object data) {
		this.resultCode = resultCode;
		this.message = message;
		this.data = data;
		this.success = true;
	}
	/**
	 * 添加成功结果信息
	 * 
	 * @param record
	 */
	public void setOK(int resultCode, Object message) {
		this.resultCode = resultCode;
		this.message = message;
		this.success = true;
		this.data = "";
	}

	/**
	 * 添加错误消息
	 * 
	 * @param message
	 */
	public void setError(int resultCode, Object message) {
		this.resultCode = resultCode;
		this.message = message;
		this.success = false;
		this.data = "";
	}
	/**
	 * 添加错误消息
	 * 
	 * @param message
	 */
	public void setError(Object message) {
		this.resultCode = ResultCode.CODE_STATE_4005;
		this.message = message;
		this.success = false;
		this.data = "";
	}
	/**
	 * 默认错误消息
	 * 
	 * @param message
	 */
	public void setErrorKefu() {
		this.resultCode = ResultCode.CODE_STATE_4005;
		this.message = "系统正在升级,如有疑问请联系客服："+GeneralConstant.KEFU;
		this.success = false;
		this.data = "";
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	public String toJsonString(){
		JSONObject json = JSONObject.fromObject(this);
		return json.toString();
	}
}
