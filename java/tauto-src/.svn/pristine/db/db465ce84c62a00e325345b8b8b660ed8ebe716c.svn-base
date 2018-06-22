package main.com.weixinHtml.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.DateUtil;
import main.com.utils.StringUtil;
import main.com.utils.rlycode.PhoneUtil;
import main.com.weixinHtml.dao.dao.ShopFindCarDao;
import main.com.weixinHtml.dao.po.ShopFindCar;
import main.com.weixinHtml.dao.po.ShopFindCarOffer;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopFindCarSearch;
import main.com.weixinHtml.dao.vo.ShopFindCarVo;
import main.com.weixinHtml.service.ShopFindCarService;

@Service
public class ShopFindCarServiceImpl extends BaseServiceImpl<ShopFindCar> implements ShopFindCarService{

	@Autowired
	ShopFindCarDao shopFindCarDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Override
	protected BaseDao<ShopFindCar> getBaseDao() {
		return shopFindCarDao;
	}

	@Override
	public Result shopFindCarEdit(ShopFindCarSearch search, Result result, ShopUsers users) throws Exception {	
		ShopFindCarVo findCarVo = new ShopFindCarVo();
		findCarVo.setCreateDate(new Date());
		findCarVo.setShopUserId(users.getShopUserId());		
		if(search.getCarsId() != null) {
			CarsVo carVo = carsDao.selectById(search.getCarsId());
			if(carVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
				return result;
			}
			findCarVo.setCarsId(carVo.getCarId());
//			StringBuffer buffer = new StringBuffer();
//				//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
//				buffer.setLength(0);
//				buffer.append(carVo.getBrandName()).append(" ");
//				buffer.append(carVo.getFamilyName()).append(" ");
//				buffer.append(carVo.getYearPattern()).append("款").append(" ");
//				buffer.append(carVo.getPl()).append(" ");
//				buffer.append(carVo.getGearboxName()).append(carVo.getStyleName());
			findCarVo.setCarsName(carVo.getCarName());
			findCarVo.setGuidancePrice(new BigDecimal(carVo.getPrice()));
			findCarVo.setImage(carVo.getIndexImage());
		}
//		else {
//			result.setError(ResultCode.CODE_STATE_4005, "请选择的车型");
//			return result;
//		}
		if(search.getColourId() != null) {
			CarColourVo colourVo = carColourDao.selectById(search.getColourId());
			if(colourVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车身颜色不存在");
				return result;
			}
			findCarVo.setColourId(colourVo.getCarColourId());
			findCarVo.setColourName(colourVo.getCarColourName());
		}
//		else {
//			result.setError(ResultCode.CODE_STATE_4005, "请选择的车身颜色");
//			return result;
//		}
		if(search.getInteriorId() != null) {
			CarInteriorVo interiorVo = carInteriorDao.selectById(search.getInteriorId());
			if(interiorVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车辆内饰不存在");
				return result;
			}
			findCarVo.setInteriorId(interiorVo.getInteriorId());
			findCarVo.setInteriorName(interiorVo.getInteriorName());
		}
//		else {
//			result.setError(ResultCode.CODE_STATE_4005, "请选择的车辆内饰");
//			return result;
//		}
		if(StringUtil.isEmpty(search.getExpectationAmount())) {
			result.setError("请输入期望价格");
			return result;
		}
		findCarVo.setExpectationAmount(search.getExpectationAmount());
		if(StringUtil.isNotEmpty(search.getSignCity())) {
			findCarVo.setSignCity(search.getSignCity());
		}
		if(StringUtil.isNotEmpty(search.getExpectationHaveingCarTime())) {
			findCarVo.setExpectationHaveingCarTime(DateUtil.format(search.getExpectationHaveingCarTime()));
		}
		if(StringUtil.isEmpty(search.getLinkmanName())) {
			result.setError("请输入联系人姓名");
			return result;
		}
		findCarVo.setLinkmanName(search.getLinkmanName());
		if(StringUtil.isEmpty(search.getLinkmanPhone())) {
			result.setError("请输入联系人电话");
			return result;
		}
		if(!PhoneUtil.isPhone(search.getLinkmanPhone())) {
			result.setError("请输入联系人电话格式错误");
			return result;
		}
		findCarVo.setLinkmanPhone(search.getLinkmanPhone());
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			findCarVo.setRemarks(search.getRemarks());
		}
		return save(findCarVo, result);
	}

	@Override
	public Result shopFindCarList(ShopFindCarSearch search, Result result, ShopUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("shopUserId", users.getShopUserId());
		params.put("sortField", true);
		List<ShopFindCarVo> applyLoanVos = shopFindCarDao.selectJoin(params);
		for(ShopFindCarVo findCarVo : applyLoanVos) {
			if(findCarVo.getFindCarOffers() == null) {
				findCarVo.setFindCarOffers(new ArrayList<ShopFindCarOffer>());
			}else {
				for(ShopFindCarOffer carOfferVo : findCarVo.getFindCarOffers()) {
					if(carOfferVo.getOverdueDate().getTime() > DateUtil.operDayDate(new Date(),1).getTime()) {
						carOfferVo.setOfferState(1);//手动设置过期
					}
				}
			}
			if(StringUtil.isEmpty(findCarVo.getImage())) {
				findCarVo.setImage("http://opii7iyzy.bkt.clouddn.com/momo.jpg");				
			}
		}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", applyLoanVos);
		return new Result(returnMap);		
	}

	@Override
	public Result shopFindCarList(ShopFindCarSearch search, Result result, SystemUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("sortField", true);
		List<ShopFindCarVo> applyLoanVos = shopFindCarDao.selectJoin(params);		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", applyLoanVos);
		return new Result(returnMap);		
	}

	@Override
	public Result shopFindCarDel(ShopFindCarSearch search, Result result, ShopUsers users) throws Exception {
		ShopFindCarVo findCarVo = shopFindCarDao.selectById(search.getFindTheCarId());
		if(findCarVo == null) {
			result.setError("你选择的记录不存在或者已删除");
			return result;
		}
		if(!findCarVo.getShopUserId().equals(users.getShopUserId())) {
			result.setError("抱歉，你无权操作此记录");
			return result;
		}
		findCarVo.setOverDel(true);
		return save(findCarVo, result);
	}

	@Override
	public Result shopFindCarUpdateState(ShopFindCarSearch search, Result result, SystemUsers users)
			throws Exception {
		ShopFindCarVo findCarVo = shopFindCarDao.selectById(search.getFindTheCarId());
		if(findCarVo == null) {
			result.setError("你选择的记录不存在或者已删除");
			return result;
		}
		findCarVo.setSystemUserId(users.getUsersId());
		findCarVo.setSystemUserName(users.getRealName());
		findCarVo.setState(1);//标记为已处理
		return save(findCarVo, result);
	}

	public ShopFindCar save(ShopFindCar shopApplyLoan) {
		if(shopApplyLoan == null) {
			return null;
		}else if(StringUtil.isEmpty(shopApplyLoan.getFindTheCarId())) {
			if(shopFindCarDao.insert(shopApplyLoan)) {
				return shopApplyLoan;
			}else {
				return null;
			}
		}else {
			if(shopFindCarDao.updateById(shopApplyLoan)) {
				return shopApplyLoan;
			}else {
				return null;
			}
		}
	}
	
	public Result save(ShopFindCar findCar,Result result)throws Exception{
		if(findCar == null) {
			result.setError("保存数据错误");
			return result;
		}else if(StringUtil.isEmpty(findCar.getFindTheCarId())) {
			if(shopFindCarDao.insert(findCar)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}else {
			if(shopFindCarDao.updateById(findCar)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}
	}

	@Override
	public Result shopFindCarInfo(ShopFindCarSearch search, Result result, ShopUsers users) throws Exception {
		ShopFindCarVo findCarVo = shopFindCarDao.selectByIdJoin(search.getFindTheCarId());
//		if(findCarVo!=null && findCarVo.getShopUsersVo() !=null && StringUtil.isNotEmpty(findCarVo.getShopUsersVo().)) {
//			
//		}
		if(findCarVo == null) {
			result.setError("你选择的记录不存在或者已删除");
			return result;
		}
		if(findCarVo.getFindCarOffers() != null && findCarVo.getFindCarOffers().size() > 0) {
			for(ShopFindCarOffer carOfferVo : findCarVo.getFindCarOffers()) {
				if(carOfferVo.getOverdueDate().getTime() > DateUtil.operDayDate(new Date(),1).getTime()) {
					carOfferVo.setOfferState(1);//手动设置过期
				}
			}
		}
		result.setOK(ResultCode.CODE_STATE_200, "操作成功",findCarVo);
		return result;
	}
}
