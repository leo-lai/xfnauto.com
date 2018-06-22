package main.com.weixinHtml.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.DateUtil;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.dao.ShopFindCarDao;
import main.com.weixinHtml.dao.dao.ShopFindCarOfferDao;
import main.com.weixinHtml.dao.po.ShopFindCarOffer;
import main.com.weixinHtml.dao.search.ShopFindCarOfferSearch;
import main.com.weixinHtml.dao.vo.ShopFindCarOfferVo;
import main.com.weixinHtml.dao.vo.ShopFindCarVo;
import main.com.weixinHtml.service.ShopFindCarOfferService;

@Service
public class ShopFindCarOfferServiceImpl extends BaseServiceImpl<ShopFindCarOffer> implements ShopFindCarOfferService{

	@Autowired
	ShopFindCarOfferDao shopFindCarOfferDao;
	
	@Autowired
	ShopFindCarDao shopFindCarDao;
	
	@Override
	protected BaseDao<ShopFindCarOffer> getBaseDao() {
		return shopFindCarOfferDao;
	}

	@Override
	@Transactional
	public Result shopFindCarOffer(ShopFindCarOfferSearch search, Result result, SystemUsers users) throws Exception {
		ShopFindCarVo findCarVo = shopFindCarDao.selectById(search.getFindCarId());
		if(findCarVo == null) {
			result.setError("你选择得寻车申请不存在或者已删除");
			return result;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("findTheCarId", findCarVo.getFindTheCarId());
		params.put("systemUserId", users.getUsersId());		
		List<ShopFindCarOfferVo> offerVos = shopFindCarOfferDao.select(params);
		if(offerVos != null && offerVos.size() > 0) {
			for(ShopFindCarOfferVo carOfferVo : offerVos) {
				if(carOfferVo.getOverdueDate().getTime() >= new Date().getTime()) {
					result.setError("你尚有未过期得报价，不能继续报价");
					return result;
				}
			}
		}
		ShopFindCarOfferVo carOfferVo = new ShopFindCarOfferVo();
		carOfferVo.setFindCarId(findCarVo.getFindTheCarId());
		carOfferVo.setSystemUserId(users.getUsersId());
		carOfferVo.setCreateDate(new Date());
		carOfferVo.setOrgId(users.getOrgId());
		carOfferVo.setOrgName(users.getOrgName());
		carOfferVo.setOfferState(0);
		carOfferVo.setSystemUserName(users.getRealName());
		carOfferVo.setSystemUserPhone(users.getPhoneNumber());
//		if(StringUtil.isNotEmpty(search.getSystemUserName())) {
//			carOfferVo.setSystemUserName(search.getSystemUserName());
//		}else {
//			result.setError("请输入联系人姓名");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getSystemUserPhone())) {
//			carOfferVo.setSystemUserPhone(search.getSystemUserPhone());
//		}else {
//			result.setError("请输入联系电话");
//			return result;
//		}
		if(StringUtil.isNotEmpty(search.getLocation())) {
			carOfferVo.setLocation(search.getLocation());
		}
		if(StringUtil.isNotEmpty(search.getOfferAmount())) {
			carOfferVo.setOfferAmount(search.getOfferAmount());
		}else {
			result.setError("请输入报价金额");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getOverdueDate())) {
			carOfferVo.setOverdueDate(DateUtil.format(search.getOverdueDate(),true));
		}else {
			result.setError("请选择过期日期");
			return result;
		}
		//更改报价单
		if(findCarVo.getState().equals(0)) {
			findCarVo.setState(1);
			try {
				if(shopFindCarDao.updateById(findCarVo)) {
					if(shopFindCarOfferDao.insert(carOfferVo)) {
						result.setOK(ResultCode.CODE_STATE_200, "报价成功");
					}else {
						result.setErrorKefu();
						throw new Exception("手动抛错回滚，报价报错");
					}
//					return save(carOfferVo, result, carOfferVo.getFindCarOfferId());
				}
				else {
					result.setErrorKefu();
					throw new Exception("手动抛错回滚，报价报错");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("手动抛错回滚，报价报错");
			}
			return result;
		}else {
			return save(carOfferVo, result, carOfferVo.getFindCarOfferId());
		}
	}

	@Override
	public Result shopFindCarOfferInfo(ShopFindCarOfferSearch search, Result result)
			throws Exception {
		ShopFindCarOfferVo offerVo = shopFindCarOfferDao.selectById(search.getFindCarOfferId());
		if(offerVo == null) {
			result.setError("你选择得寻车申请报价不存在或者已删除");
			return result;
		}
		if(offerVo.getOverdueDate().getTime() < DateUtil.operDayDate(new Date(),1).getTime()) {
			offerVo.setOfferState(1);//手动设置过期
		}
		result.setOK(ResultCode.CODE_STATE_200, "", offerVo);
		return result;
	}

	@Override
	public Result myShopFindCarOffer(ShopFindCarOfferSearch search, Result result, SystemUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("systemUserId", users.getUsersId());
		List<ShopFindCarOfferVo> carOfferVos = shopFindCarOfferDao.selectJoin(params);
		if(carOfferVos != null && carOfferVos.size() > 0) {
			for(ShopFindCarOfferVo carOfferVo : carOfferVos) {
				if(carOfferVo.getOverdueDate().getTime() < DateUtil.operDayDate(new Date(),1).getTime()) {
					carOfferVo.setOfferState(1);//手动设置过期
				}
			}
		}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", carOfferVos);
		return new Result(returnMap);		
	}

}
