package main.com.logistics.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.CarInterior;
import main.com.constant.LogisticsDistributionState;
import main.com.constant.LogisticsGoodsCarState;
import main.com.constant.LogisticsconsignmentState;
import main.com.exception.BusinessException;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.constant.DeliveryState;
import main.com.logistics.constant.DrivateState;
import main.com.logistics.constant.LogisticsCarState;
import main.com.logistics.dao.dao.LogisticsCarDao;
import main.com.logistics.dao.dao.LogisticsConsignmentDao;
import main.com.logistics.dao.dao.LogisticsDistributionDao;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.po.LogisticsDriver;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.search.CarInDistributionSearch;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LoadCarSearch;
import main.com.logistics.dao.search.LoadCarSubSearch;
import main.com.logistics.dao.search.LogisticsGoodsCarSearch;
import main.com.logistics.dao.search.MakeCarArrivedSearch;
import main.com.logistics.dao.search.SignCarSearch;
import main.com.logistics.dao.search.SignCarSubSearch;
import main.com.logistics.dao.search.UnloadCarSearch;
import main.com.logistics.dao.search.UnloadCarSubSearch;
import main.com.logistics.dao.search.UpdateDistributionStateSearch;
import main.com.logistics.dao.search.UploadPicSearch;
import main.com.logistics.dao.vo.LogisticsCarVo;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsDistributionVo;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.logistics.service.LogisticsDistributionService;
import main.com.logistics.service.LogisticsDriverService;
import main.com.logistics.service.LogisticsGoodsCarService;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.utils.Base64Util;
import main.com.utils.ConvertUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.UUIDUtils;
import main.com.utils.UserInfoUtil;
import main.com.utils.rlycode.RLYUtils;


/** 
* @author liaozijie 
* @version 创建时间：2018年1月15日 下午5:06:51 
* 类描述： 
*/
@Service
public class LogisticsDriverServiceImpl implements LogisticsDriverService {
	
	@Autowired
	private LogisticsConsignmentDao logisticsConsignmentDao;
	
	@Autowired
	private LogisticsDistributionDao logisticsDistributionDao;
	
	@Autowired
	private LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Autowired
	private CarColourDao carColorDao;
	
	@Autowired
	private CarInteriorDao carInteriroDao;
	
	@Autowired
	private LogisticsDistributionService logisticsDistributionService;
	
	@Autowired
	private LogisticsGoodsCarService logisticsGoodsCarService;
	
	@Autowired
	private LogisticsDriverDao logisticsDriverDao;
	
	@Autowired
	private LogisticsCarDao logisticsCarDao;
	
	@Override
	public Result login(SystemUsersSearch search, Result result, HttpSession session) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
			params.put("phoneNumber", search.getPhoneNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4003, "请输入电话号码");
			return result;
		}
		params.put("isStatus", true);
		params.put("isEnable", true);
		List<LogisticsDriverVo> driverVos = logisticsDriverDao.select(params);
		if(driverVos == null || driverVos.size() == 0){
			result.setError(ResultCode.CODE_STATE_4003, "用户不存在");
			return result;
		}
		LogisticsDriverVo logisticsDriverVo = driverVos.get(0);
		if(!logisticsDriverVo.getPassword().equals(MD5Encoder.encodeByMD5(search.getPassword()))){
			result.setError(ResultCode.CODE_STATE_4003, "密码错误");
			return result;
		}
		logisticsDriverVo.setSessionId(UUIDUtils.random());
		logisticsDriverVo.setNikeName(Base64Util.encodeData(search.getNikeName()));
		logisticsDriverVo.setHeadPortrait(search.getHeadPortrait());
		
		JSONObject jsonObject = UserInfoUtil.getDriverInfo(search.getCode(), search.getEncryptedData(), search.getIv());
		if(jsonObject != null) {
			if(jsonObject.has("openId")){
				logisticsDriverVo.setOpenId(String.valueOf(jsonObject.get("openId")));
			}
			if(jsonObject.has("gender")) {
				logisticsDriverVo.setAgentGender(Integer.valueOf(String.valueOf(jsonObject.get("gender"))));
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		if(logisticsDriverDao.updateById(logisticsDriverVo)){
			map.put("cardNo", logisticsDriverVo.getCardNo());
			map.put("phoneNumber", logisticsDriverVo.getPhoneNumber());
			map.put("agentGender", logisticsDriverVo.getAgentGender());
			map.put("realName", logisticsDriverVo.getRealName());
			map.put("sessionId", logisticsDriverVo.getSessionId());
			map.put("orgName", logisticsDriverVo.getOrgName());
			map.put("OpenId", logisticsDriverVo.getOpenId());
			map.put("headPortrait", logisticsDriverVo.getHeadPortrait());			
			map.put("idcardPicOn", logisticsDriverVo.getIdcardPicOn());			
			map.put("idcardPicOff", logisticsDriverVo.getIdcardPicOff());			
			map.put("nikeName", Base64Util.encodeData(logisticsDriverVo.getNikeName()));			
			result.setOK(ResultCode.CODE_STATE_200, "登录成功", TakeCareMapDate.cutNullMap(map));
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}

	@Override
	public Result loginOut(SystemUsersSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		LogisticsDriverVo driverVo = logisticsDriverDao.selectByCode(search.getSessionId());
		if(driverVo == null){
			result.setError(ResultCode.CODE_STATE_4003, "用户信息错误");
			return result;
		}
		driverVo.setSessionId("");
//		systemUsers.setLoginTime("");
		if(logisticsDriverDao.updateById(driverVo)){
			params.clear();
			result.setOK(ResultCode.CODE_STATE_200, "登出成功", params);
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}

	@Override
	public Result changePassword(SystemUsersSearch search, Result result, HttpSession session) throws Exception {
		LogisticsDriverVo logisticsDriverVo = logisticsDriverDao.selectByCode(search.getSessionId());
		if(logisticsDriverVo == null){
			result.setError(ResultCode.CODE_STATE_4003, "用户信息错误");
			return result;
		}
		if(!logisticsDriverVo.getPassword().equals(MD5Encoder.encodeByMD5(search.getPasswordOld()))){
			result.setError(ResultCode.CODE_STATE_4003, "用户旧密码错误");
			return result;
		}
		logisticsDriverVo.setPassword(MD5Encoder.encodeByMD5(search.getPassword()));
		logisticsDriverVo.setSessionId(UUIDUtils.random());
		if(logisticsDriverDao.updateById(logisticsDriverVo)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cardNo", logisticsDriverVo.getCardNo());
			map.put("phoneNumber", logisticsDriverVo.getPhoneNumber());
			map.put("agentGender", logisticsDriverVo.getAgentGender());
			map.put("realName", logisticsDriverVo.getRealName());
			map.put("sessionId", logisticsDriverVo.getSessionId());
			map.put("orgName", logisticsDriverVo.getOrgName());
			map.put("OpenId", logisticsDriverVo.getOpenId());
			map.put("headPortrait", logisticsDriverVo.getHeadPortrait());			
			map.put("idcardPicOn", logisticsDriverVo.getIdcardPicOn());			
			map.put("idcardPicOff", logisticsDriverVo.getIdcardPicOff());			
			map.put("nikeName", Base64Util.encodeData(logisticsDriverVo.getNikeName()));
			result.setOK(ResultCode.CODE_STATE_200, "密码修改成功", map);
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}
	
	
	
	@Override
	public Result queryDriverDistributionDetails(DriverDistributionSearch search) throws Exception {
		// TODO Auto-generated method stub
        Result distributionResult = logisticsDistributionService.queryDriverDistributions(search);
        if(distributionResult.isSuccess()){
            List<LogisticsDistributionVo> vos = new ArrayList<>();
            ConvertUtil.listObjectToListObject((List<LogisticsDistributionVo>)distributionResult.getData(),vos,LogisticsDistributionVo.class);
            Iterator<LogisticsDistributionVo> it = vos.iterator();
            while(it.hasNext()) {
            	LogisticsDistributionVo v = it.next();
            	CarInDistributionSearch carInDistributionSearch = new CarInDistributionSearch();
            	carInDistributionSearch.setDistributionCode(v.getDistributionCode());
            	carInDistributionSearch.setDistributionState(v.getDistributionState());
            	Result goosCarResult = logisticsDistributionService.queryCarInDistribution(carInDistributionSearch);
                if(goosCarResult.isSuccess()){
                    v.setGoodsCarVos((List<LogisticsGoodsCarVo>)goosCarResult.getData());
                }else {
                	it.remove();
                }
            }
            if(!vos.isEmpty()) {
            	return new Result(vos);
            }
        }
        return new Result(ResultCode.CODE_STATE_200,false,"无对应配送单");
	}
	
	@Override
	@Transactional
	public Result startDelivery(Integer distributionId) throws Exception{
		// TODO Auto-generated method stub
		LogisticsDistribution distribution = logisticsDistributionService.getById(distributionId);
		if(distribution == null) 
			return new Result(ResultCode.CODE_STATE_200,false,"无对应配送单");
	
		CarInDistributionSearch carInDistributionSearch = new CarInDistributionSearch();
    	carInDistributionSearch.setDistributionId(distribution.getDistributionId());
    	carInDistributionSearch.setDistributionState(distribution.getDistributionState());
    	Result goosCarResult = logisticsDistributionService.queryCarInDistribution(carInDistributionSearch);
    	if(goosCarResult.isSuccess()) {
    		List<LogisticsGoodsCarVo> vos = (List<LogisticsGoodsCarVo>)goosCarResult.getData();
    		for(LogisticsGoodsCarVo carVO : vos) {
    			//更新货物车状态
    			LogisticsGoodsCar car = new LogisticsGoodsCar();
    			car.setGoodsCarId(carVO.getGoodsCarId());
    			car.setGoodsCarState(DeliveryState.DELIVERYING.getCode());//配送中状态
    			logisticsGoodsCarService.edit(car);
    		}
    	}
    	//更新配送单状态
    	distribution.setDistributionState(DeliveryState.DELIVERYING.getCode());//状态：配送中
    	logisticsDistributionService.edit(distribution);
		
		return new Result(ResultCode.CODE_STATE_200,true,"成功开始配送");
	}
	
	@Override
	@Transactional
	public Result endDelivary(Integer distributionId) throws Exception {
		// TODO Auto-generated method stub
		LogisticsDistribution distribution = logisticsDistributionService.getById(distributionId);
		if(distribution == null) 
			return new Result(ResultCode.CODE_STATE_200,false,"无对应配送单");
	
		CarInDistributionSearch carInDistributionSearch = new CarInDistributionSearch();
    	carInDistributionSearch.setDistributionId(distribution.getDistributionId());
    	carInDistributionSearch.setDistributionState(distribution.getDistributionState());
    	Result goosCarResult = logisticsDistributionService.queryCarInDistribution(carInDistributionSearch);
    	if(goosCarResult.isSuccess()) {
    		List<LogisticsGoodsCarVo> vos = (List<LogisticsGoodsCarVo>)goosCarResult.getData();
    		StringBuilder errorMsg = new StringBuilder();
    		for(LogisticsGoodsCarVo carVO : vos) {
    			//检查货物车是否为配送中状态
    			if(!Objects.equals(carVO.getGoodsCarState(),DeliveryState.DELIVERYING.getCode())) {
    				errorMsg.append("品牌车系为" + carVO.getBrandName() + carVO.getFamilyName() + "的车辆不在配送中状态;");
    			}
    			//检查有无上传卸车图片
    			if(StringUtil.isEmpty(carVO.getAcceptImage())) {
    				errorMsg.append("品牌车系为" + carVO.getBrandName() + carVO.getFamilyName() + "的车辆没有上传装车图片;");
    			}
    			//检查有无上传卸车图片
    			if(StringUtil.isEmpty(carVO.getDeliverToImage())) {
    				errorMsg.append("品牌车系为" + carVO.getBrandName() + carVO.getFamilyName() + "的车辆没有上传卸车图片;");
    			}
    			//更新货物车状态
    			LogisticsGoodsCar car = new LogisticsGoodsCar();
    			car.setGoodsCarId(carVO.getGoodsCarId());
    			car.setGoodsCarState(DeliveryState.END.getCode());
    			logisticsGoodsCarService.edit(car);
    		}
    		
    		if(!StringUtil.isEmpty(errorMsg.toString())) {
    			throw new BusinessException(ResultCode.CODE_STATE_4006, errorMsg.toString());
    		}
    	}
    	
    	//更新配送单状态
    	distribution.setDistributionState(DeliveryState.END.getCode());
    	logisticsDistributionService.edit(distribution);
    	
    	return new Result(ResultCode.CODE_STATE_200,true,"配送完成");
	}
	
	@Override
	public Result uploadPic(UploadPicSearch search) {
		// TODO Auto-generated method stub
		LogisticsGoodsCar car = new LogisticsGoodsCar();
		car.setGoodsCarId(search.getGoodsCarId());
		if(Objects.equals(search.getType(),1)) {
			car.setAcceptImage(search.getPicUrl());
		}
		if(Objects.equals(search.getType(),2)) {
			car.setDeliverToImage(search.getPicUrl());
		}
		
		if(logisticsGoodsCarService.edit(car)) {
			return new Result(true,"上传图片成功");
		}
		return new Result(false,"上传图片失败");
	}
	
	@Override
	public Result listLogisticsDistribution(String keywords) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("keywords", keywords);
		List<LogisticsDistribution> distributions = logisticsDistributionDao.list(map);
		List<LogisticsDistributionVo> distributionVos = new ArrayList<>(distributions.size());
		ConvertUtil.listObjectToListObject(distributions, distributionVos, LogisticsDistributionVo.class);
		for(LogisticsDistributionVo v : distributionVos) {
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", v.getDistributionId());
			List<LogisticsGoodsCar> cars = logisticsDistributionDao.queryCarInDistribution(m1);
			List<LogisticsGoodsCarVo> carVOs = new ArrayList<>(cars.size());
			ConvertUtil.listObjectToListObject(cars, carVOs, LogisticsGoodsCarVo.class);
			List<LogisticsConsignmentVo> consignmentVOs = new ArrayList<>();
			//分组
			Map<Integer, List<LogisticsGoodsCarVo>> m2 =
					carVOs.stream().collect(Collectors.groupingBy(LogisticsGoodsCarVo::getConsignmentId));
			for(Map.Entry<Integer, List<LogisticsGoodsCarVo>> entry : m2.entrySet()) {
				Integer consignmentId = entry.getKey();
				LogisticsConsignment consignment = logisticsConsignmentDao.selectById(consignmentId);
				LogisticsConsignmentVo consignmentVO = new LogisticsConsignmentVo();
				ConvertUtil.objectToObject(consignment, consignmentVO);
				consignmentVO.setGoodsCarVos(entry.getValue());
				consignmentVOs.add(consignmentVO);
			}
			v.setLogisticsConsignmentVos(consignmentVOs);		
		}
		return new Result(distributionVos);
	}
	
	@Override
	public Result updateDistributionState(UpdateDistributionStateSearch search) {
		// TODO Auto-generated method stub
		LogisticsDistribution distribution = logisticsDistributionDao.selectById(search.getDistributionId());
		Integer state = search.getState();
		String msg = LogisticsDistributionState.getMsgByCode(state);
		if(msg.isEmpty()) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "无效物流单状态");
		}
		
		//初始
		if(Objects.equals(state, LogisticsDistributionState.Init.getCode())) {
			
		}
		//已派单
		if(Objects.equals(state, LogisticsDistributionState.Distributed.getCode())) {
			
		}
		List<LogisticsGoodsCar> cars = null;
		//已接单
		if(Objects.equals(state, LogisticsDistributionState.OrderReceived.getCode())) {
			//更新车辆状态为已接单
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			for(LogisticsGoodsCar car : cars) {
				car.setGoodsCarState(LogisticsGoodsCarState.OrderReceived.getCode());
				logisticsGoodsCarDao.updateById(car);
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(cars.get(0).getConsignmentId());
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.OrderReceived.getCode()) && !cars.contains(carVo)) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.OrderReceived.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
		}
		//已装车
		if(Objects.equals(state, LogisticsDistributionState.Loaded.getCode())) {
			//检车车辆状态是否全为已装车
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			for(LogisticsGoodsCar car : cars) {
				if(!StringUtil.isNotEmptyMoreThenZero(car.getColourId())) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, car.getCarsName()+"缺少车身颜色");
				}
				if(!StringUtil.isNotEmptyMoreThenZero(car.getInteriorId())) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, car.getCarsName()+"缺少内饰颜色");
				}
				if(!Objects.equals(car.getGoodsCarState(), LogisticsGoodsCarState.Loaded.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "车辆未装车");
				}
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(cars.get(0).getConsignmentId());
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.Loaded.getCode()) && !cars.contains(carVo)) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Loaded.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
		}
		//运输中
		if(Objects.equals(state, LogisticsDistributionState.Transporting.getCode())) {
			//更新车辆状态未运输中
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			for(LogisticsGoodsCar car : cars) {
				car.setGoodsCarState(LogisticsGoodsCarState.Transporting.getCode());
				logisticsGoodsCarDao.updateById(car);
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(cars.get(0).getConsignmentId());
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.Transporting.getCode()) && !cars.contains(carVo)) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Transporting.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
		}
		//到达目的地
		if(Objects.equals(state, LogisticsDistributionState.Arrived.getCode())) {
			//更新车辆状态为到达目的地
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			Set<Integer> set = new HashSet<>();
			for(LogisticsGoodsCar car : cars) {
				set.add(car.getConsignmentId());
			}
			Map<String, Object> params = new HashMap<>();
			params.put("ids", set);
			List<LogisticsConsignmentVo> consignmentVos = logisticsConsignmentDao.select(params);
			for(LogisticsGoodsCar car : cars) {
				car.setGoodsCarState(LogisticsGoodsCarState.Arrived.getCode());
				logisticsGoodsCarDao.updateById(car);
			}
			for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
				//发送短信通知
				RLYUtils.sendTemplateNotice(consignmentVo.getPurchasersPhone(), "239006", new String[] {});
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(cars.get(0).getConsignmentId());
//			if() {
//				
//			}
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.Arrived.getCode()) && !cars.contains(carVo)) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Arrived.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
		}
		//已卸车
		if(Objects.equals(state, LogisticsDistributionState.Unloaded.getCode())) {
			//检车车辆状态是否全为已卸车
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			for(LogisticsGoodsCar car : cars) {
				if(!Objects.equals(car.getGoodsCarState(), LogisticsGoodsCarState.Unloaded.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "车辆未卸车");
				}
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(cars.get(0).getConsignmentId());
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.Unloaded.getCode()) && !cars.contains(carVo)) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Unloaded.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
		}
		//已签收
		if(Objects.equals(state, LogisticsDistributionState.Signed.getCode())) {
			//检车车辆状态是否全为已签收
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			for(LogisticsGoodsCar car : cars) {
				if(!Objects.equals(car.getGoodsCarState(), LogisticsGoodsCarState.Signed.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "车辆未签收");
				}
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(cars.get(0).getConsignmentId());
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.Signed.getCode()) && !cars.contains(carVo)) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Signed.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
			if(distribution.getDestinationType().equals(GeneralConstant.ConsignmentState.TYPELINE)) {
				LogisticsDriverVo logisticsDriverVo = logisticsDriverDao.selectById(distribution.getDriverId());
				LogisticsCarVo logisticsCarVo = logisticsCarDao.selectById(distribution.getLogisticsCarId());
				if(logisticsCarVo != null) {//专线签收完毕就置司机为空闲，专线在更早前就执行了此操作
					logisticsCarVo.setLogisticsCarState(LogisticsCarState.FREE.getCode());
					logisticsCarDao.updateById(logisticsCarVo);
				}								
				if(logisticsDriverVo != null) {//专线签收完毕就置司机为空闲，专线在更早前就执行了此操作
					logisticsDriverVo.setStatus(DrivateState.FREE.getCode());
					logisticsDriverDao.updateById(logisticsDriverVo);
				}								
			}
		}
		
		//完成
		if(Objects.equals(state, LogisticsDistributionState.Done.getCode())) {
			//检查车辆状态是否为已收钱
			Map<String, Object> m1 = new HashMap<>();
			m1.put("distributionId", distribution.getDistributionId());
			cars = logisticsDistributionDao.queryCarInDistribution(m1);
			for(LogisticsGoodsCar car : cars) {
				if(!Objects.equals(car.getGoodsCarState(), LogisticsGoodsCarState.Paid.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "车辆:"+car.getCarsName()+"未支付");
				}
			}
			//
			if(distribution.getDestinationType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {
				LogisticsDriverVo logisticsDriverVo = logisticsDriverDao.selectById(distribution.getDriverId());
				LogisticsCarVo logisticsCarVo = logisticsCarDao.selectById(distribution.getLogisticsCarId());
				if(logisticsCarVo != null) {//点对点付款完毕就置司机为空闲，专线在更早前就执行了此操作
					logisticsCarVo.setLogisticsCarState(LogisticsCarState.FREE.getCode());
					logisticsCarDao.updateById(logisticsCarVo);
				}								
				if(logisticsDriverVo != null) {//点对点付款完毕就置司机为空闲，专线在更早前就执行了此操作
					logisticsDriverVo.setStatus(DrivateState.FREE.getCode());
					logisticsDriverDao.updateById(logisticsDriverVo);
				}								
			}
		}
	
		distribution.setDistributionState(search.getState());
		logisticsDistributionDao.updateById(distribution);
		return new Result("操作成功");
	}
	
	@Override
	public Result loadCar(LoadCarSearch search) {
		// TODO Auto-generated method stub
		List<LoadCarSubSearch> cars = search.getCars();
		List<LogisticsGoodsCar> goodsCars = new ArrayList<>();
		//查询当前物流单的所有车架号
		if(StringUtil.isEmpty(search.getDistributionId())) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "未知的物流单，请刷新重试");
		}
		Map<String, Object> m = new HashMap<>();
		m.put("distributionId", search.getDistributionId());
		List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//		Set<String> set = new HashSet<>();
//		Set<Integer> setId = new HashSet<>();
		for(LogisticsGoodsCarVo goodsCarVo : goodsCars_c) {
			if(StringUtil.isEmpty(goodsCarVo.getFrameNumber())) {
				continue;
			}
			for(LoadCarSubSearch car : cars) {
				if(car.getVin().equals(goodsCarVo.getFrameNumber()) && (StringUtil.isEmpty(car.getGoodsCarId()) || !car.getGoodsCarId().equals(goodsCarVo.getGoodsCarId()))) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "车架号："+car.getVin()+"在物流单中存在重复，请检查");
				}
			}
		}
		for(LoadCarSubSearch car : cars) {
			Integer goodsCarId = car.getGoodsCarId();
			if(StringUtil.isEmpty(car.getAcceptImage())) {
				throw new BusinessException(ResultCode.CODE_STATE_4005, "请上传装车照片");
			}
//			if(StringUtil.isEmpty(car.getVin())) {
//				throw new BusinessException(ResultCode.CODE_STATE_4005, "请填写完整车架号");
//			}else if(!set.add(car.getVin())) {
//				if(StringUtil.isEmpty(car.getGoodsCarId()) || setId.add(car.getGoodsCarId())) {
//					throw new BusinessException(ResultCode.CODE_STATE_4005, "车架号："+car.getVin()+"在物流单中存在重复，请检查");
//				}
//			}
			LogisticsGoodsCar goodsCar = logisticsGoodsCarDao.selectById(goodsCarId);
			if(goodsCar == null) {
				throw new BusinessException(ResultCode.CODE_STATE_4005, "托运车辆不存在");
			}
			CarColour color = carColorDao.selectById(car.getColorId());			
			if(color != null) {
				goodsCar.setColourId(color.getCarColourId());
				goodsCar.setColourName(color.getCarColourName());
//				throw new BusinessException(ResultCode.CODE_STATE_4005, "颜色不存在");
			}
			CarInterior interior = carInteriroDao.selectById(car.getInteriorId());
			if(interior != null) {
				goodsCar.setInteriorId(interior.getInteriorId());
				goodsCar.setInteriorName(interior.getInteriorName());
//				throw new BusinessException(ResultCode.CODE_STATE_4005, "内饰颜色不存在");
			}
			goodsCar.setFrameNumber(car.getVin());
			goodsCar.setAcceptImage(car.getAcceptImage());
			goodsCar.setFollowInformation(car.getFollowInformation());
			
			//更新车辆状态为已装车
			goodsCar.setGoodsCarState(LogisticsGoodsCarState.Loaded.getCode());
			goodsCars.add(goodsCar);	
		}
		for(LogisticsGoodsCar car : goodsCars) {
			logisticsGoodsCarDao.updateById(car);		
		}
		return new Result("操作成功");
	}
	
	@Override
	public Result makeCarArrived(MakeCarArrivedSearch search) {
		// TODO Auto-generated method stub
		Map<String, Object> m1 = ConvertUtil.objectToMap(search);
		List<LogisticsGoodsCar> cars = logisticsGoodsCarDao.select(m1);
		//查询相应的物流单
		Set<Integer> set = new HashSet<>();
		for(LogisticsGoodsCar car : cars) {
			set.add(car.getConsignmentId());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("ids", set);
		List<LogisticsConsignmentVo> consignmentVos = logisticsConsignmentDao.select(params);
		for(LogisticsGoodsCar car : cars) {
			car.setGoodsCarState(LogisticsGoodsCarState.Arrived.getCode());
			logisticsGoodsCarDao.updateById(car);
		}
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			//发送短信通知
			RLYUtils.sendTemplateNotice(consignmentVo.getPurchasersPhone(), "239006", new String[] {});
		}
		return new Result("操作成功");
	}
	
	@Override
	public Result unloadCar(LoadCarSearch search) {
		// TODO Auto-generated method stub
		List<LogisticsGoodsCar> goodsCars = new ArrayList<>();		
		for(LoadCarSubSearch s : search.getCars()) {
			if(StringUtil.isEmpty(s.getAcceptImage())) {
				throw new BusinessException(ResultCode.CODE_STATE_4005, "请上传卸车照片");
			}
			LogisticsGoodsCar car = logisticsGoodsCarDao.selectById(s.getGoodsCarId());
			if(car == null) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
			}
			car.setDeliverToImage(s.getAcceptImage());
			//更新车辆状态为已卸车
			car.setGoodsCarState(LogisticsGoodsCarState.Unloaded.getCode());
			goodsCars.add(car);
		}
		for(LogisticsGoodsCar car : goodsCars) {
			logisticsGoodsCarDao.updateById(car);		
		}
		return new Result("操作成功");
	}
	
	@Override
	@Transactional
	public Result signCar(SignCarSearch search) {
		Set<Integer> set = new HashSet<>();
		for(SignCarSubSearch s : search.getCars()) {
			set.add(s.getGoodsCarId());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("goodsCarIds", set);
		List<LogisticsGoodsCar> goodsCars = logisticsGoodsCarDao.select(params);
		if(goodsCars == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "请先选择车辆");
		}
		for(LogisticsGoodsCar goodsCar : goodsCars) {
			if(goodsCar.getGoodsCarState() < LogisticsGoodsCarState.Unloaded.getCode()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆："+goodsCar.getCarsName()+"尚未卸车");
			}
			if(goodsCar.getGoodsCarState() > LogisticsGoodsCarState.Unloaded.getCode()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆："+goodsCar.getCarsName()+"已历过卸车状态，现已处于签收或支付，不能进行重复签收");
			}
		}
		for(SignCarSubSearch s : search.getCars()) {
			for(LogisticsGoodsCar goodsCar : goodsCars) {
				if(s.getGoodsCarId().equals(goodsCar.getGoodsCarId())) {
					if(StringUtil.isEmpty(s.getSignPic())) {
						throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆："+goodsCar.getCarsName()+"尚未卸车");
					}
					goodsCar.setGoodsCarState(LogisticsGoodsCarState.Signed.getCode());
					goodsCar.setSignPic(s.getSignPic());
					goodsCar.setSignName(s.getSignName());
				}
			}
		}
		try {
			for(LogisticsGoodsCar goodsCar : goodsCars) {
				if(!logisticsGoodsCarDao.updateById(goodsCar)) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return new Result("签收完成");
	}
	
	/**
	 * 备份
	 * @param search
	 * @return
	 */
	public Result signCar_back(SignCarSearch search) {
		// TODO Auto-generated method stub
		for(SignCarSubSearch s : search.getCars()) {
			LogisticsGoodsCar car = logisticsGoodsCarDao.selectById(s.getGoodsCarId());
			if(car == null) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
			}
			if(car.getGoodsCarState() < LogisticsGoodsCarState.Unloaded.getCode()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆："+car.getCarsName()+"尚未卸车");
			}
//			LogisticsConsignmentVo consignmentVo = logisticsConsignmentDao.selectById(car.getConsignmentId());
//			//根据物流单查询所有的车辆
//			Map<String, Object> m = new HashMap<>();
//			m.put("consignmentId", consignmentVo.getConsignmentId());
//			List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(m);
//			for(LogisticsGoodsCarVo carVo : goodsCars_c) {
//				if(!carVo.getGoodsCarState().equals(LogisticsconsignmentState.Signed.getCode()) && !carVo.getGoodsCarId().equals(car.getGoodsCarId())) {
//					consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Signed.getCode());
//					logisticsConsignmentDao.updateById(consignmentVo);
//					break;
//				}
//			}
//			LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectById(car.getDistributionId());
//			LogisticsDriverVo driverVo = null;
//			if(distributionVo.getDestinationType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {
//				driverVo = logisticsDriverDao.selectById(distributionVo.getDriverId());
//			}
			//根据物流单查询所有的车辆
//			m.clear();
//			m.put("distributionId", distributionVo.getDistributionId());
//			List<LogisticsGoodsCarVo> goodsCars_d = logisticsGoodsCarDao.select(m);
//			Boolean is = true;
//			for(LogisticsGoodsCarVo carVo : goodsCars_d) {
//				if(!carVo.getGoodsCarState().equals(LogisticsGoodsCarState.Signed.getCode()) && !car.getGoodsCarId().equals(carVo.getGoodsCarId())) {
//					is = false;
//				}
//			}
//			if(is) {
//				distributionVo.setDistributionState(LogisticsDistributionState.Signed.getCode());
//				logisticsDistributionDao.updateById(distributionVo);
//				if(distributionVo.getDestinationType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {		
//					if(driverVo != null) {//点对点付款完毕就置司机为空闲，专线在更早前就执行了此操作
//						driverVo.setStatus(DrivateState.FREE.getCode());
//						logisticsDriverDao.updateById(driverVo);
//					}								
//				}
//			}
			car.setSignPic(s.getSignPic());
			//更新车辆状态为已签收
			car.setGoodsCarState(LogisticsGoodsCarState.Signed.getCode());
			logisticsGoodsCarDao.updateById(car);
		}
		return new Result("操作成功");
	}

	@Override
	public LogisticsDriverVo getByCode(String sessionId) throws Exception {
		return logisticsDriverDao.selectByCode(sessionId);
	}
}
