package main.com.weixinHtml.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShopApplyLoan;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;

public interface ShopApplyLoanService extends BaseService<ShopApplyLoan>{

	/**
	 * 贷款列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanList(ShopApplyLoanSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 贷款列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanList(ShopApplyLoanSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 贷款详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanInfo(ShopApplyLoanSearch search, Result result)throws Exception;

	/**
	 * 编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanEdit(ShopApplyLoanSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 取消/删除
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanDel(ShopApplyLoanSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 审核
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanAudit(ShopApplyLoanSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 商户贷款前置
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanBefor(ShopApplyLoanSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 商户垫资申请
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result applyLoanMerchant(ShopApplyLoanSearch search, Result result, ShopUsers users)throws Exception;

}
