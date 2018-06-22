package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;
import main.com.weixinHtml.service.ShopApplyLoanService;
/**
 * 商城贷款
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/applyLoan")
public class ShopApplyLoanController extends BaseController{

	@Autowired
	ShopApplyLoanService applyLoanService;
	
	/**
	 * 贷款列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myApplyLoanList")
	public Result myApplyLoanList(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = applyLoanService.applyLoanList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 全部贷款列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanList")
	public Result applyLoanList(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = applyLoanService.applyLoanList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 贷款详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanInfo")
	public Result applyLoanInfo(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
//			ShopUser users = supplementSearchShopUser(search.getSessionId());
			result = applyLoanService.applyLoanInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 个人贷款申请
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanEdit")
	public Result applyLoanEdit(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = applyLoanService.applyLoanEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 贷款删除
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanDel")
	public Result applyLoanDel(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = applyLoanService.applyLoanDel(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 贷款审核
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanAudit")
	public Result applyLoanAudit(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = applyLoanService.applyLoanAudit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 贷款申请前置（商户）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanBefor")
	public Result applyLoanBefor(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = applyLoanService.applyLoanBefor(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 垫资申请（商户）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanMerchant")
	public Result applyLoanMerchant(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = applyLoanService.applyLoanMerchant(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
