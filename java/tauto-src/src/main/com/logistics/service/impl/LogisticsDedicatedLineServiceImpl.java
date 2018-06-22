package main.com.logistics.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsDedicatedLineDao;
import main.com.logistics.dao.dao.LogisticsDynamicLineDao;
import main.com.logistics.dao.po.LogisticsDedicatedLine;
import main.com.logistics.dao.search.LogisticsDedicatedLineSearch;
import main.com.logistics.dao.vo.LogisticsDedicatedLineVo;
import main.com.logistics.dao.vo.LogisticsDynamicLineVo;
import main.com.logistics.service.LogisticsDedicatedLineService;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class LogisticsDedicatedLineServiceImpl extends BaseServiceImpl<LogisticsDedicatedLine> implements LogisticsDedicatedLineService{

	@Autowired
	LogisticsDedicatedLineDao logisticsDedicatedLineDao;
	
	@Autowired
	LogisticsDynamicLineDao logisticsDynamicLineDao;
	
	@Override
	protected BaseDao<LogisticsDedicatedLine> getBaseDao() {
		return logisticsDedicatedLineDao;
	}

	@Override
	public Result dedicatedLineList(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params =  getParams(search,users);
		List<LogisticsDedicatedLineVo> dedicatedLineVos = logisticsDedicatedLineDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = logisticsDedicatedLineDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsDedicatedLineVo dedicatedLineVo : dedicatedLineVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("dedicatedLineName", dedicatedLineVo.getDedicatedLineName());
			map.put("dedicatedLineId", dedicatedLineVo.getDedicatedLineId());
			map.put("departureTime", dedicatedLineVo.getDepartureTime());
			map.put("startingPointAddress", dedicatedLineVo.getStartingPointAddress());
			map.put("destinationAddress", dedicatedLineVo.getDestinationAddress());
			map.put("destinationLatitude", dedicatedLineVo.getDestinationLatitude());
			map.put("destinationLongitude", dedicatedLineVo.getDestinationLongitude());
			map.put("startingPointLatitude", dedicatedLineVo.getStartingPointLatitude());
			map.put("startingPointLongitude", dedicatedLineVo.getStartingPointLongitude());
			map.put("createDate", dedicatedLineVo.getCreateDate()!=null?DateUtil.format(dedicatedLineVo.getCreateDate()):"");
			map.put("remarks", dedicatedLineVo.getRemarks());
			map.put("amount", dedicatedLineVo.getAmount()!= null?dedicatedLineVo.getAmount().doubleValue():0.0d);
			map.put("additionalAmount", dedicatedLineVo.getAdditionalAmount()!= null?dedicatedLineVo.getAdditionalAmount().doubleValue():0.0d);
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getParams(LogisticsDedicatedLineSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result dedicatedLineEdit(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsDedicatedLineVo dedicatedLineVo = null;
		if(StringUtil.isEmpty(search.getDedicatedLineId())) {
			dedicatedLineVo = new LogisticsDedicatedLineVo();
			dedicatedLineVo.setOverDelete(false);
			dedicatedLineVo.setCreateDate(new Date());
			dedicatedLineVo.setOrgId(users.getOrgId());
			dedicatedLineVo.setOrgName(users.getOrgName());
		}else {
			dedicatedLineVo = logisticsDedicatedLineDao.selectById(search.getDedicatedLineId());
			if(dedicatedLineVo == null) {
				result.setError("你选择的资费不存在或者已删除，请重新选择");
				return result;
			}
			if(!dedicatedLineVo.getOrgId().equals(users.getOrgId())) {
				result.setError("抱歉，该信息不属于贵司，你无权删除");
				return result;
			}
		}
		if(StringUtil.isNotEmpty(search.getDedicatedLineName())) {
			dedicatedLineVo.setDedicatedLineName(search.getDedicatedLineName());
		}else {
			result.setError("请输入专线名称");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStartingPointAddress())) {
			dedicatedLineVo.setStartingPointAddress(search.getStartingPointAddress());
		}else {
			result.setError("请输入出发地点");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDestinationAddress())) {
			dedicatedLineVo.setDestinationAddress(search.getDestinationAddress());
		}else {
			result.setError("请输入到达地点");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDepartureTime())) {
			dedicatedLineVo.setDepartureTime(search.getDepartureTime());
		}else {
			result.setError("请选择正常出发时间");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getAmount())) {
			dedicatedLineVo.setAmount(new BigDecimal(search.getAmount()));
		}else {
			result.setError("请填写运输费用");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getAdditionalAmount())) {
			dedicatedLineVo.setAdditionalAmount(new BigDecimal(search.getAdditionalAmount()));
		}else {
			dedicatedLineVo.setAdditionalAmount(new BigDecimal(0.0d));
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			dedicatedLineVo.setRemarks(search.getRemarks());
		}
		if(StringUtil.isNotEmpty(search.getStartingPointLatitude())) {
			dedicatedLineVo.setStartingPointLatitude(search.getStartingPointLatitude());
		}else {
			result.setError("缺失起点经度，不能保存");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStartingPointLongitude())) {
			dedicatedLineVo.setStartingPointLongitude(search.getStartingPointLongitude());
		}else {
			result.setError("缺失起点纬度，不能保存");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDestinationLatitude())) {
			dedicatedLineVo.setDestinationLatitude(search.getDestinationLatitude());
		}else {
			result.setError("缺失终点经度，不能保存");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDestinationLongitude())) {
			dedicatedLineVo.setDestinationLongitude(search.getDestinationLongitude());
		}else {
			result.setError("缺失终点纬度度，不能保存");
			return result;
		}
		if(StringUtil.isEmpty(search.getDedicatedLineId())) {
			return logisticsDedicatedLineDao.insertAndResultIT(dedicatedLineVo, result);
		}else {
			return logisticsDedicatedLineDao.updateByIdAndResultIT(dedicatedLineVo, result);
		}
	}

	@Override
	public Result dedicatedLineDelete(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getDedicatedLineId())) {
			result.setError("请先选择你要删除的记录");
			return result;
		}
		LogisticsDedicatedLineVo dedicatedLineVo = logisticsDedicatedLineDao.selectById(search.getDedicatedLineId());
		if(dedicatedLineVo == null) {
			result.setError("你选择的资费不存在或者已删除，请重新选择");
			return result;
		}
		if(!dedicatedLineVo.getOrgId().equals(users.getOrgId())) {
			result.setError("抱歉，该信息不属于贵司，你无权删除");
			return result;
		}
		dedicatedLineVo.setOverDelete(true);
		return logisticsDedicatedLineDao.updateByIdAndResultIT(dedicatedLineVo, result);
	}

	@Override
	public Result LogisticsLineList(LogisticsDedicatedLineSearch search, Result result) throws Exception {
		//查询点对点配置
		Map<String,Object> params = new HashMap<>();
//		params.put("orgId", );
		Map<String,Object> allMap = new HashMap<String, Object>();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();

		List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.select(params);
		if(dynamicLineVos !=null && dynamicLineVos.size() > 0) {
			Map<String,Object> dynamicMap = new HashMap<String, Object>();	
			LogisticsDynamicLineVo dynamicLineVo = dynamicLineVos.get(0);
			dynamicMap.put("lineId", dynamicLineVo.getDynamicLineId());
			dynamicMap.put("startPrice", dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0.0d);
			dynamicMap.put("amount", dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0.0d);
			dynamicMap.put("consignmentType", GeneralConstant.ConsignmentState.TYPEPOINT);
			dynamicMap.put("lineName", "普通物流方式");
			maps.add(dynamicMap);
		}
		
		
		//查询专线
//		Map<String,Object> params =  new HashMap<>();
		List<LogisticsDedicatedLineVo> dedicatedLineVos = logisticsDedicatedLineDao.select(params);		
		for(LogisticsDedicatedLineVo dedicatedLineVo : dedicatedLineVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("lineName", dedicatedLineVo.getDedicatedLineName());
			map.put("lineId", dedicatedLineVo.getDedicatedLineId());
			map.put("startingPointAddress", dedicatedLineVo.getStartingPointAddress());
			map.put("startingPointLatitude", dedicatedLineVo.getStartingPointLatitude());
			map.put("startingPointLongitude", dedicatedLineVo.getStartingPointLongitude());
			map.put("destinationAddress", dedicatedLineVo.getDestinationAddress());
			map.put("destinationLatitude", dedicatedLineVo.getDestinationLatitude());
			map.put("destinationLongitude", dedicatedLineVo.getDestinationLongitude());
//			map.put("departureTime", dedicatedLineVo.getDepartureTime());			
			map.put("amount", dedicatedLineVo.getAmount()!= null?dedicatedLineVo.getAmount().doubleValue():0.0d);
			map.put("consignmentType", GeneralConstant.ConsignmentState.TYPELINE);
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
}
