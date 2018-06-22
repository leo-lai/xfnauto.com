package main.com.system.aspect;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.exception.BusinessException;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月17日 下午2:39:38 
* 类描述： 统一处理异常
*/
@ControllerAdvice
public class ExceptionAspect {
	
	protected static final Logger logger = Logger.getLogger(ExceptionAspect.class);
	
	/**
	 * 捕获BusinessException
	 * date: 2018年1月17日 下午2:42:36
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Result handlerBusinessException(BusinessException e) {
		return new Result(e.getErrorCode(),false,e.getErrorMsg());
	}
	
	/**
	 * 捕获顶层异常
	 * date: 2018年1月17日 下午2:45:14
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result handlerGlobalException(Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
		Result result = new Result();
		result.setError(ResultCode.CODE_STATE_4005, "系统正在升级");
		return result;
	}
}
