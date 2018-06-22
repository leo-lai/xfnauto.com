package main.com.weixinHtml.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.vo.CarsVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.Base64Util;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.rlycode.PhoneUtil;
import main.com.weixinHtml.constant.ShopApplyLoanState;
import main.com.weixinHtml.dao.dao.ShopApplyLoanDao;
import main.com.weixinHtml.dao.po.ShopApplyLoan;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;
import main.com.weixinHtml.dao.vo.ShopApplyLoanVo;
import main.com.weixinHtml.service.ShopApplyLoanService;

@Service
public class ShopApplyLoanServiceImpl extends BaseServiceImpl<ShopApplyLoan> implements ShopApplyLoanService{

	@Autowired
	ShopApplyLoanDao shopApplyLoanDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Override
	protected BaseDao<ShopApplyLoan> getBaseDao() {
		return shopApplyLoanDao;
	}

	@Override
	public Result applyLoanList(ShopApplyLoanSearch search, Result result, ShopUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("shopUserId", users.getShopUserId());
		params.put("sortField", true);
		List<ShopApplyLoanVo> applyLoanVos = shopApplyLoanDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", applyLoanVos);
		return new Result(returnMap);		
	}

	@Override
	public Result applyLoanList(ShopApplyLoanSearch search, Result result, SystemUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("orgId", users.getOrgId());
		params.put("sortField", true);
		List<ShopApplyLoanVo> applyLoanVos = shopApplyLoanDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", applyLoanVos);
		return new Result(returnMap);
	}

	@Override
	public Result applyLoanInfo(ShopApplyLoanSearch search, Result result) throws Exception {
		if(StringUtil.isEmpty(search.getApplyLoanId())) {
			result.setError("请先选择贷款记录");
			return result;
		}
		ShopApplyLoanVo applyLoanVo = shopApplyLoanDao.selectByIdJoin(search.getApplyLoanId());
		if(applyLoanVo == null) {
			result.setError("你选择的记录不存在或者已删除，请重新选择");
			return result;
		}
		applyLoanVo.setCreateTimeForMat(DateUtil.format(applyLoanVo.getCreateTime()));
		result.setOK(ResultCode.CODE_STATE_200, "", applyLoanVo);
		return result;
	}

	@Override
	public Result applyLoanEdit(ShopApplyLoanSearch search, Result result, ShopUsers users) throws Exception {
		if(!users.getUserType().equals(1)) {
			result.setError("抱歉，您的账号是商户账户，不在个人贷款业务内");
			return result;
		}
//		Map<String, Object> params = new HashMap<>();
//		params.put("shopUserId", users.getShopUserId());
//		List<ShopApplyLoanVo> applyLoanVos = shopApplyLoanDao.select(params);
//		if(applyLoanVos != null && applyLoanVos.size() > 0) {
//			result.setError("你尚有贷款正在等待受理，暂不能再发起贷款申请");
//			return result;
//		}
		ShopApplyLoanVo applyLoanVo = new ShopApplyLoanVo();
		applyLoanVo.setCreateTime(new Date());
		applyLoanVo.setLoanType(ShopApplyLoanState.loan.getCode());
		applyLoanVo.setShopUserId(users.getShopUserId());
		applyLoanVo.setShopUserName(users.getRealName());
		applyLoanVo.setPhoneNumber(users.getPhoneNumber());
		applyLoanVo.setOrgId(61);
		applyLoanVo.setOrgName("淘车网");
		applyLoanVo.setIsDel(false);
		
		if(StringUtil.isEmpty(search.getCarsId())) {
			result.setError("请选择车型");
			return result;
		}
		CarsVo carVo = carsDao.selectById(search.getCarsId());
		if(carVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
			return result;
		}
		applyLoanVo.setCarsId(carVo.getCarId());
		applyLoanVo.setCarsName(carVo.getCarName());
		applyLoanVo.setGuidancePrice(new BigDecimal(carVo.getPrice()));
		applyLoanVo.setImage(carVo.getIndexImage());
//		applyLoanVo.setCarsId(search.getCarsId());
//		if(StringUtil.isEmpty(search.getGuidancePrice())) {
//			result.setError("车辆指导价错误");
//			return result;
//		}
//		applyLoanVo.setGuidancePrice(search.getGuidancePrice());
		if(StringUtil.isNotEmpty(search.getInstitutionId())) {
			switch (search.getInstitutionId()) {
			case GeneralConstant.LoanBank.CHERY:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.CHERY);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.CHERY_NAME);
				break;
			case GeneralConstant.LoanBank.GONGSHANG:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.GONGSHANG);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.GONGSHANG_NAME);
				break;
			case GeneralConstant.LoanBank.HESHANZHUJIANG:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.HESHANZHUJIANG);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.HESHANZHUJIANG_NAME);
				break;
			case GeneralConstant.LoanBank.JIANSHE:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.JIANSHE);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.JIANSHE_NAME);
				break;
			case GeneralConstant.LoanBank.NONGYE:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.NONGYE);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.NONGYE_NAME);
				break;
			case GeneralConstant.LoanBank.RUIFUDE:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.RUIFUDE);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.RUIFUDE_NAME);
				break;
			case GeneralConstant.LoanBank.GUNAGZHOU:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.GUNAGZHOU);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.GUNAGZHOU_NAME);
				break;
			case GeneralConstant.LoanBank.HESHANNONGCUN:
				applyLoanVo.setInstitutionId(GeneralConstant.LoanBank.HESHANNONGCUN);
				applyLoanVo.setInstitutionName(GeneralConstant.LoanBank.HESHANNONGCUN_NAME);
				break;
			default:
				result.setError("你选择的贷款金融机构或银行错误，请重新选择");
				return result;
			}
		}
//		else {
//			result.setError("请选择贷款金融机构或银行");
//			return result;
//		}
//		if(StringUtil.isEmpty(search.getInstitutionId())) {
//			result.setError("请选择金融机构");
//			return result;
//		}
//		applyLoanVo.setInstitutionId(search.getInstitutionId());
		if(StringUtil.isEmpty(search.getDownPayments())) {
			result.setError("请输入首付比例");
			return result;
		}
		applyLoanVo.setDownPayments(search.getDownPayments());
		if(StringUtil.isEmpty(search.getLoanPeriod())) {
			if(search.getLoanPeriod().doubleValue() >= 1.0d) {
				result.setError("还款期数");
				return result;
			}
		}
		applyLoanVo.setLoanPeriod(search.getLoanPeriod());
		if(StringUtil.isEmpty(search.getLoanAmount())) {
			result.setError("请输入贷款金额");
			return result;
		}
		applyLoanVo.setLoanAmount(search.getLoanAmount());
		
		if(StringUtil.isEmpty(search.getLoanPeopleName())) {
			result.setError("请输入申请人姓名");
			return result;
		}
		applyLoanVo.setLoanPeopleName(search.getLoanPeopleName());
		if(StringUtil.isEmpty(search.getLoanPeoplePhone())) {
			result.setError("请输入申请人电话");
			return result;
		}
		if(!PhoneUtil.isPhone(search.getLoanPeoplePhone())) {
			result.setError("电话号格式错误");
			return result;
		}
		applyLoanVo.setLoanPeoplePhone(search.getLoanPeoplePhone());
//		if(StringUtil.isEmpty(search.getLoanPeriod())) {
//			result.setError("请输入还款期数");
//			return result;
//		}
//		applyLoanVo.setLoanPeriod(search.getLoanPeriod());
		if(StringUtil.isNotEmpty(search.getAnnualIncome())) {
			applyLoanVo.setAnnualIncome(search.getAnnualIncome());
//			result.setError("请输入年收入金额");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getAnnualIncomeImage())) {
			applyLoanVo.setAnnualIncomeImage(search.getAnnualIncomeImage());
//			result.setError("请输入年收入金额");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getIdCardPicOff())) {
			applyLoanVo.setIdCardPicOff(search.getIdCardPicOff());
//			result.setError("请输入年收入金额");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getIdCardPicOn())) {
			applyLoanVo.setIdCardPicOn(search.getIdCardPicOn());
//			result.setError("请输入年收入金额");
//			return result;
		}
		return save(applyLoanVo, result);
	}

	@Override
	public Result applyLoanDel(ShopApplyLoanSearch search, Result result, ShopUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getApplyLoanId())) {
			result.setError("请先选择贷款记录");
			return result;
		}
		ShopApplyLoanVo applyLoanVo = shopApplyLoanDao.selectByIdJoin(search.getApplyLoanId());
		if(applyLoanVo == null) {
			result.setError("你选择的记录不存在或者已删除，请重新选择");
			return result;
		}
		if(!applyLoanVo.getShopUserId().equals(users.getShopUserId())) {
			result.setError("你选择的记录不存在或者已删除，请重新选择");
			return result;
		}
		applyLoanVo.setIsDel(true);
		return save(applyLoanVo, result);
	}

	@Override
	public Result applyLoanAudit(ShopApplyLoanSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getApplyLoanId())) {
			result.setError("请先选择贷款记录");
			return result;
		}
		ShopApplyLoanVo applyLoanVo = shopApplyLoanDao.selectByIdJoin(search.getApplyLoanId());
		if(applyLoanVo == null) {
			result.setError("你选择的记录不存在或者已删除，请重新选择");
			return result;
		}
		if(!users.getOrgId().equals(applyLoanVo.getOrgId())) {
			result.setError("抱歉，你没有此记录的操作权限");
			return result;
		}
		applyLoanVo.setAuditTime(new Date());
		if(search.getIsPass() != null && search.getIsPass()) {
			applyLoanVo.setLoneState(ShopApplyLoanState.pass.getCode());
		}else {
			applyLoanVo.setLoneState(ShopApplyLoanState.refuse.getCode());
			if(StringUtil.isEmpty(search.getRefusalReason())) {
				result.setError("请输入拒绝理由");
				return result;
			}
			applyLoanVo.setRefusalReason(search.getRefusalReason());
		}
		applyLoanVo.setSystemUserId(users.getUsersId());
		applyLoanVo.setSystemUserName(users.getRealName());
		return save(applyLoanVo, result);
	}

	public ShopApplyLoan save(ShopApplyLoan shopApplyLoan) {
		if(shopApplyLoan == null) {
			return null;
		}else if(StringUtil.isEmpty(shopApplyLoan.getApplyLoanId())) {
			if(shopApplyLoanDao.insert(shopApplyLoan)) {
				return shopApplyLoan;
			}else {
				return null;
			}
		}else {
			if(shopApplyLoanDao.updateById(shopApplyLoan)) {
				return shopApplyLoan;
			}else {
				return null;
			}
		}
	}
	
	public Result save(ShopApplyLoan applyLoan,Result result)throws Exception{
		if(applyLoan == null) {
			result.setError("保存数据错误");
			return result;
		}else if(StringUtil.isEmpty(applyLoan.getApplyLoanId())) {
			if(shopApplyLoanDao.insert(applyLoan)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}else {
			if(shopApplyLoanDao.updateById(applyLoan)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}
	}

	@Override
	public Result applyLoanBefor(ShopApplyLoanSearch search, Result result, ShopUsers users) throws Exception {
		if(!users.getUserType().equals(2)) {
			result.setError("抱歉，您的账号是商户账户，不在个人贷款业务内");
			return result;
		}
		Map<String, Object> orgParams = new HashMap<>();
		orgParams.put("orgId", users.getOrgId());
		List<Organization> organizations = organizationDao.select(orgParams);
		if(organizations == null || organizations.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "", "");
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "", organizations.get(0));
		}
		return result;
	}

	@Override
	public Result applyLoanMerchant(ShopApplyLoanSearch search, Result result, ShopUsers users) throws Exception {
		if(!users.getUserType().equals(2)) {
			result.setError("抱歉，您的账号是个人账户，不在垫资业务范围内");
			return result;
		}
		Map<String, Object> orgParams = new HashMap<>();
		orgParams.put("orgId", users.getOrgId());
		orgParams.put("status", 1);//GeneralConstant.Org.
		List<Organization> organizations = organizationDao.select(orgParams);
		if(organizations == null || organizations.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "抱歉，你的商户账号未审核通过或者已禁用,请联系客服："+GeneralConstant.KEFU);
			return result;
		}
		ShopApplyLoanVo applyLoanVo = new ShopApplyLoanVo();
		applyLoanVo.setCreateTime(new Date());
		applyLoanVo.setLoanType(ShopApplyLoanState.advance.getCode());
		applyLoanVo.setShopUserId(users.getShopUserId());
		applyLoanVo.setShopUserName(users.getRealName());
		applyLoanVo.setPhoneNumber(users.getPhoneNumber());
		applyLoanVo.setOrgId(61);
		applyLoanVo.setOrgName("淘车网");
		if(StringUtil.isEmpty(search.getCarsId())) {
			result.setError("请选择车型");
			return result;
		}
		CarsVo carVo = carsDao.selectById(search.getCarsId());
		if(carVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
			return result;
		}
		applyLoanVo.setCarsId(carVo.getCarId());
		applyLoanVo.setCarsName(carVo.getCarName());
		applyLoanVo.setGuidancePrice(new BigDecimal(carVo.getPrice()));
		applyLoanVo.setImage(carVo.getIndexImage());

		if(StringUtil.isEmpty(search.getCarNumber())) {
			result.setError("车辆数量错误");
			return result;
		}
		applyLoanVo.setCarNumber(search.getCarNumber());
		if(StringUtil.isEmpty(search.getLoanAmount())) {
			result.setError("请输入垫资总额");
			return result;
		}
		applyLoanVo.setLoanAmount(search.getLoanAmount());
		return save(applyLoanVo,result);
	}
}
