package main.com.weixinApp.controller;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.utils.QiniuUtils;
import main.com.utils.StringUtil;
import main.com.weixinApp.service.EmployeeCustomerService;

/**
 * 员工端 --顾客
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeCustomerController extends BaseController{
	public static Logger logger = Logger.getLogger(EmployeeCustomerController.class);

	@Autowired
	EmployeeCustomerService employeeService;
	
	/**
	 * 获取自身或者下级架构列表（下拉列表）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationLevelList", method = RequestMethod.POST)
	public Result organizationLevelList(OrganizationSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.organizationLevelList(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 利用电话号码搜索
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerPhoneSearchList")
	public Result phoneCustomerSearchList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = employeeService.phoneCustomerSearchList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 预约客户列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bespeakCustomerOrgList")
	public Result bespeakCustomerOrgList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = employeeService.customerOrgList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 全部客户列表
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerUserList")
	public Result customerUserList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = employeeService.customerUserList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 我的顾客订单列表（销售顾问）
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myCustomerOrderList")
	public Result myCustomerOrderList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = employeeService.myCustomerOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 落定客户/贷款通过客户/待完款客户/待加装上牌客户/待提车客户
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderStateCustomerList")
	public Result orderStateCustomerList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = employeeService.orderStateCustomerList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 新增客户
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCustomerUsersr")
	@LogPoint(logDes = "员工端顾客管理模块", operateModule = "新增客户")
	public Result addCustomerUsersr(CustomerUsersSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = employeeService.addCustomerUsersr(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 获取客户详细信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerUsersrInfo", method = RequestMethod.POST)
	public Result customerUsersrInfo(CustomerUsersSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.customerUsersrInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 修改个人信息
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeUserInfo")
	@LogPoint(logDes = "员工端顾客管理模块", operateModule = "修改客户个人信息")
	public Result changeUserInfo(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.changeUserInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 添加备注
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCustomerRemarks", method = RequestMethod.POST)
	@LogPoint(logDes = "员工端顾客管理模块", operateModule = "添加备注")
	public Result addCustomerRemarks(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.addCustomerRemarks(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
    /**
     * 文件上传（图片和视频）
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Result uploadQiNiu(MultipartHttpServletRequest request,
	    HttpSession session) throws Exception {
    	Result result = new Result();
    		CommonsMultipartFile myfile = (CommonsMultipartFile) request
    				.getFile("img_file");
    		 byte[] buffer = myfile.getBytes();
    		 String fileName = myfile.getOriginalFilename();
    			int dot = fileName.lastIndexOf('.');
    			String suffix = fileName.substring(dot).toLowerCase();// 后缀名
    	    	String path = QiniuUtils.uploadFileBackPath(buffer, System.currentTimeMillis()+"", null);//默认未images空间
//    	    	System.out.println("上传图片路径："+path);
    		 if(StringUtil.isEmpty(path)){
    			 result.setError(ResultCode.CODE_STATE_4009, "上传文件出错");
    		 }else{
    			 result.setOK(ResultCode.CODE_STATE_200,"",path);
    		 }
	return result;
    }
    
    /**
     * 提交银行审核资料
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addBankAudits")
    @ResponseBody
    @LogPoint(logDes = "员工端顾客管理模块", operateModule = "提交银行审核资料")
    public Result addBankAudits(CustomerUsersSearch search,
    		HttpSession session) throws Exception {
    	Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.addBankAudits(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
    }
    
	/**
	 * 更改销售顾问
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systenUserChangeCustomerOrg")
	@LogPoint(logDes = "员工端顾客管理模块", operateModule = "更改销售顾问")
	public Result systenUserChangeCustomerOrg(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.systenUserChangeCustomerOrg(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 开单前置，基本信息查询
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createOrderBefor")
	@LogPoint(logDes = "员工端顾客管理模块", operateModule = "编辑用户订车单")
	public Result createOrderBefor(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.createOrderBefor(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
