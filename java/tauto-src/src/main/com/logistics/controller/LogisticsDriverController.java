package main.com.logistics.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LoadCarSearch;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.search.LogisticsDriverSearch;
import main.com.logistics.dao.search.MakeCarArrivedSearch;
import main.com.logistics.dao.search.SignCarSearch;
import main.com.logistics.dao.search.UnloadCarSearch;
import main.com.logistics.dao.search.UpdateDistributionStateSearch;
import main.com.logistics.dao.search.UploadPicSearch;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.service.LogisticsDriverService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.service.SystemUsersService;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.weixinApp.service.EmployeeConsignmentService;
import main.com.weixinApp.service.EmployeeDistributionService;

/** 
* @author liaozijie  小程序司机端
* @version 创建时间：2018年1月15日 下午5:03:13 
* 类描述： 物流司机模块controller
*/
@Controller
@RequestMapping(value = "/driver")
public class LogisticsDriverController extends BaseController{
	
	@Autowired
	private LogisticsDriverService logisticsDriverService;
	
	@Autowired
	EmployeeDistributionService employeeService;
	
	@Autowired
	EmployeeConsignmentService consignmentService;
	
//	@Autowired
//	private SystemUsersService systemUsersService;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "用户登陆")
	public Result login(SystemUsersSearch search) throws Exception{
		Result result = new Result();
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入电话号码");
			return result;
		}
		if(checkUpParameter(search.getPassword(), result, "登录密码"))return result;
		HttpSession session = request.getSession();
		return logisticsDriverService.login(search,result,session);
		
	}
	
	/**
	 * 用户登出
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginOut")
	@LogPoint(logDes = "司机端模块", operateModule = "用户注销登陆")
	public Result loginOut(SystemUsersSearch search) throws Exception{
		Result result = new Result();
		if(checkUpParameter(search.getSessionId(), result, "登录凭据不能为空"))return result;
		return logisticsDriverService.loginOut(search,result);
	}
	
	/**
	 * 用户修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changePassword")
	@LogPoint(logDes = "司机端模块", operateModule = "用户修改密码")
	public Result changePassword(SystemUsersSearch search) throws Exception{
		Result result = new Result();
		if(checkUpParameter(search.getPasswordOld(), result, "旧密码不能为空"))return result;
		if(checkUpParameter(search.getPassword(), result, "新密码"))return result;
		HttpSession session = request.getSession();
		return logisticsDriverService.changePassword(search,result,session);
	}
	
//	/**
//	 * 查询司机配送单
//	 * @param driverId
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/queryDriverDistributions")
//	@ResponseBody
//	public Result queryDriverDistributions(DriverDistributionSearch search) throws Exception{
//		if(Objects.isNull(search.getDistributionState())) {
//			return new Result(ResultCode.CODE_STATE_4005,false,"配送状态不能为空");
//		}
//		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
//		search.setDriverId(user.getUsersId());
//		return logisticsDriverService.queryDriverDistributionDetails(search);
//	}	
	
	/**
	 * 配送单列表
	 * @param menuSearch
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/distributionList")
	public Result distributionList(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			LogisticsDriverVo users = supplementSearchLogisticsDriver(search.getSessionId());
			search.setDriverId(users.getDriverId());
			search.setDistributionStateMAX(GeneralConstant.DistributionState.ING);
			result = employeeService.distributionList(search,result,new SystemUsers());
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 开始配送
	 * date: 2018年1月17日 上午11:59:20
	 * @param distributionId
	 * @return
	 */
	@RequestMapping(value = "/startDelivery")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "开始配送")
	public Result startDelivery(Integer distributionId) throws Exception{
		return logisticsDriverService.startDelivery(distributionId);
	}
	
	/**
	 * 完成配送
	 * date: 2018年1月17日 下午12:00:30
	 * @param distributionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/endDelivery")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "完成配送")
	public Result endDelivery(Integer distributionId) throws Exception{
		return logisticsDriverService.endDelivary(distributionId);
	}
	
	/**
	 * 货物车上传图片
	 * date: 2018年1月16日 下午5:34:11
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/uploadPic")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "货物车上传图片")
	public Result uploadPic(UploadPicSearch search) {
		return logisticsDriverService.uploadPic(search);
	}
	
	/**
	 * 更新物流单状态
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/updateDistributionState")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "更新物流单状态")
	public Result updateDistributionState(UpdateDistributionStateSearch search) {
		return logisticsDriverService.updateDistributionState(search);
	}
	
	/**
	 * 获取物流单列表
	 * @return
	 */
	@RequestMapping(value = "/listLogisticsDistribution")
	@ResponseBody
	public Result listLogisticsDistribution(String keywords) {
		return logisticsDriverService.listLogisticsDistribution(keywords);
	}
	
	
	/**
	 * 装车
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/loadCar")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "装车")
	public Result loadCar(@RequestBody LoadCarSearch search) {
		return logisticsDriverService.loadCar(search);
	}
	
	/**
	 * 卸车
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/unloadCar")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "卸车")
	public Result unloadCar(@RequestBody LoadCarSearch search) {
		return logisticsDriverService.unloadCar(search);
	}
	
	/**
	 * 签收
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/signCar")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "签收")
	public Result unloadCar(@RequestBody SignCarSearch search) {
		return logisticsDriverService.signCar(search);
	}
	
	/**
	 * 到达目的地
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/makeCarArrived")
	@ResponseBody
	@LogPoint(logDes = "司机端模块", operateModule = "到达目的地")
	public Result makeCarArrived(MakeCarArrivedSearch search) {
		return logisticsDriverService.makeCarArrived(search);
	}

	
	/**
	 * 配送单详情
	 * @param menuSearch
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/distributionInfo")
	public Result distributionInfo(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			result = employeeService.distributionInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 接单
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionOrderTaking")
	@LogPoint(logDes = "司机端模块", operateModule = "接单")
	public Result distributionDelivery(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			LogisticsDriverVo users = supplementSearchLogisticsDriver(search.getSessionId());
			result = employeeService.distributionOrderTaking(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentInfo")
	public Result consignmentInfo(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			result = consignmentService.consignmentInfo(search,result,null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 根据配送单ID查看物流实时位置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsDistributionGPS")
	public Result logisticsDistributionGPS(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			result = consignmentService.logisticsDistributionGPS(search,result,null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 根据物流单ID查看物流实时位置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsConsignmentGPS")
	public Result logisticsConsignmentGPS(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			result = consignmentService.logisticsConsignmentGPS(search,result,null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单发起支付(POS)
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsInPayPOS")
	@LogPoint(logDes = "司机端模块", operateModule = "托运单发起支付")
	public Result consignmentInPayPOS(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			result = employeeService.logisticsInPayPOS(search,result,null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
