package main.com.logistics.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsDynamicLineDao;
import main.com.logistics.dao.dao.LogisticsDynamicLineInfoDao;
import main.com.logistics.dao.po.LogisticsDynamicLine;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.search.LogisticsDynamicLineSearch;
import main.com.logistics.dao.vo.LogisticsDynamicLineInfoVo;
import main.com.logistics.dao.vo.LogisticsDynamicLineVo;
import main.com.logistics.service.LogisticsDynamicLineService;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class LogisticsDynamicLineServiceImpl extends BaseServiceImpl<LogisticsDynamicLine> implements LogisticsDynamicLineService{

	@Autowired
	LogisticsDynamicLineDao logisticsDynamicLineDao;
	
	@Autowired
	LogisticsDynamicLineInfoDao logisticsDynamicLineInfoDao;
	
	@Override
	protected BaseDao<LogisticsDynamicLine> getBaseDao() {
		return logisticsDynamicLineDao;
	}

	@Override
	public Result dynamicLineInfo(LogisticsDynamicLineSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(users.getOrgId())) {
			result.setError("你的公司信息错误，无法进行此操作，请联系管理员");
			return result;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("orgId", users.getOrgId());
		List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.selectJoin(params);
		Map<String,Object> map = new HashMap<>();
		if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
			dynamicLineVos = logisticsDynamicLineDao.select(params);
			if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
				result.setOK(ResultCode.CODE_STATE_200, "", map);
				return result;
			}
		}
		LogisticsDynamicLineVo dynamicLineVo = dynamicLineVos.get(0);
		map.put("dynamicLineId", dynamicLineVo.getDynamicLineId());
		map.put("gradeFirst", dynamicLineVo.getGradeFirst()!=null?dynamicLineVo.getGradeFirst().doubleValue():0.0d);
		map.put("gradeFive", dynamicLineVo.getGradeFive()!=null?dynamicLineVo.getGradeFive().doubleValue():0.0d);
		map.put("gradeFour", dynamicLineVo.getGradeFour()!=null?dynamicLineVo.getGradeFour().doubleValue():0.0d);
		map.put("gradeSecond", dynamicLineVo.getGradeSecond()!=null?dynamicLineVo.getGradeSecond().doubleValue():0.0d);
		map.put("gradeSix", dynamicLineVo.getGradeSix()!=null?dynamicLineVo.getGradeSix().doubleValue():0.0d);
		map.put("gradeThird", dynamicLineVo.getGradeThird()!=null?dynamicLineVo.getGradeThird().doubleValue():0.0d);
		map.put("startingMileage", dynamicLineVo.getStartingMileage());
		map.put("startPrice", dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0.0d);
		map.put("additionalAmount", dynamicLineVo.getAdditionalAmount()!=null?dynamicLineVo.getAdditionalAmount().doubleValue():0.0d);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		if(dynamicLineVo.getLineInfoVos() != null && dynamicLineVo.getLineInfoVos().size() > 0) {
			if(dynamicLineVo.getLineInfoVos().size() > 1) {//有多条纪律，排序
				Collections.sort(dynamicLineVo.getLineInfoVos(),new Comparator<LogisticsDynamicLineInfoVo>(){//由小到
		            public int compare(LogisticsDynamicLineInfoVo arg0, LogisticsDynamicLineInfoVo arg1) {
		                return arg0.getMinMileage().compareTo(arg1.getMinMileage());
		            }
		        });
			}
			for(LogisticsDynamicLineInfoVo infoVo : dynamicLineVo.getLineInfoVos()) {
				Map<String,Object> infoMap = new HashMap<>();
				infoMap.put("amount", infoVo.getAmount()!=null?infoVo.getAmount().doubleValue():0.0d);
				infoMap.put("dynamicLineInfoId", infoVo.getDynamicLineInfoId());
				infoMap.put("minMileage", infoVo.getMinMileage());
				infoMap.put("maxMileage", infoVo.getMaxMileage());
				maps.add(TakeCareMapDate.cutNullMap(infoMap));
			}
			map.put("list", maps);
		}else {
			map.put("list", maps);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	@Transactional
	public Result dynamicLineEdit(LogisticsDynamicLineSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(users.getOrgId())) {
			result.setError("你的公司信息错误，无法进行此操作，请联系管理员");
			return result;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("orgId", users.getOrgId());
		LogisticsDynamicLineVo dynamicLineVo = null;
		List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.selectJoin(params);
		if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
			dynamicLineVos = logisticsDynamicLineDao.select(params);
			if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
				dynamicLineVo = new LogisticsDynamicLineVo();
				dynamicLineVo.setOrgId(users.getOrgId());
				dynamicLineVo.setOrgName(users.getOrgName());
			}else {
				dynamicLineVo = dynamicLineVos.get(0);
			}
		}else {
			dynamicLineVo = dynamicLineVos.get(0);
		}
		if(StringUtil.isNotEmpty(search.getGradeFirst())) {
			dynamicLineVo.setGradeFirst(new BigDecimal(search.getGradeFirst()));
		}
		if(StringUtil.isNotEmpty(search.getGradeFive())) {
			dynamicLineVo.setGradeFive(new BigDecimal(search.getGradeFive()));
		}
		if(StringUtil.isNotEmpty(search.getGradeFour())) {
			dynamicLineVo.setGradeFour(new BigDecimal(search.getGradeFour()));
		}
		if(StringUtil.isNotEmpty(search.getGradeSecond())) {
			dynamicLineVo.setGradeSecond(new BigDecimal(search.getGradeSecond()));
		}
		if(StringUtil.isNotEmpty(search.getGradeSix())) {
			dynamicLineVo.setGradeSix(new BigDecimal(search.getGradeSix()));
		}
		if(StringUtil.isNotEmpty(search.getGradeThird())) {
			dynamicLineVo.setGradeThird(new BigDecimal(search.getGradeThird()));
		}
		if(StringUtil.isNotEmpty(search.getStartPrice())) {
			dynamicLineVo.setStartPrice(new BigDecimal(search.getStartPrice()));
		}else {
			result.setError("请填写起步价格");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStartingMileage())) {
			dynamicLineVo.setStartingMileage(search.getStartingMileage());
		}else {
			result.setError("请填写起步里程");
			return result;
		}
//		if(StringUtil.isEmpty(search.getDynamicLineInfo())) {
//			result.setError("请至少配置一个溢出里程与价格");
//			return result;
//		}
		if(StringUtil.isNotEmpty(search.getAdditionalAmount())) {
			dynamicLineVo.setAdditionalAmount(new BigDecimal(search.getAdditionalAmount()));
		}else {
			dynamicLineVo.setAdditionalAmount(new BigDecimal(0.0d));
		}
		
//		String[] dynamicLineInfoes = search.getDynamicLineInfo().split(GeneralConstant.SystemBoolean.SPLIT_);
		List<LogisticsDynamicLineInfoVo> dynamicLineInfoVos = search.getList();
		if(dynamicLineInfoVos == null) {
			dynamicLineInfoVos = new ArrayList<>();
		}else if(dynamicLineInfoVos.size() > 1){//如果配置了两个以上
			Collections.sort(dynamicLineInfoVos,new Comparator<LogisticsDynamicLineInfoVo>(){//由小到
	            public int compare(LogisticsDynamicLineInfoVo arg0, LogisticsDynamicLineInfoVo arg1) {
	                return arg0.getMinMileage().compareTo(arg1.getMinMileage());
	            }
	        });
		}
		 for(int i=0;i<dynamicLineInfoVos.size();i++) {
			 LogisticsDynamicLineInfoVo lineInfoVo = dynamicLineInfoVos.get(i);
			 if(lineInfoVo.getMaxMileage() != 0d && lineInfoVo.getMaxMileage() <= lineInfoVo.getMinMileage()) {
				 result.setMessage("溢出里程配置错误:终止里程"+lineInfoVo.getMaxMileage()+"小于或等于起始里程："+lineInfoVo.getMinMileage());
				 return result;
			 }
			 if(i+1 < dynamicLineInfoVos.size()) {
				 if(dynamicLineInfoVos.get(i+1).getMinMileage() < lineInfoVo.getMaxMileage()) {
					 result.setMessage("溢出里程配置错误:其中包含终止里程"+lineInfoVo.getMaxMileage()+"大于起始里程："+dynamicLineInfoVos.get(i+1).getMinMileage());
					 return result;
				 }
			 }
	 	}
//		for(String dynamicLineInfo : dynamicLineInfoes) {
//			String[] infoes = dynamicLineInfo.split(GeneralConstant.SystemBoolean.SPLIT);
//			LogisticsDynamicLineInfoVo infoVo = new LogisticsDynamicLineInfoVo();
//			infoVo.setAmount(new BigDecimal(infoes[0]));
//			infoVo.setMinMileage(Double.valueOf(infoes[1]));
//			infoVo.setMaxMileage(Double.valueOf(infoes[2]));
//			dynamicLineInfoVos.add(infoVo);
//		}
		
		try {
//			if(dynamicLineVo.getLineInfoVos() != null && dynamicLineVo.getLineInfoVos().size() > 0) {//刪除原有紀錄
//				for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineVo.getLineInfoVos()) {
//					logisticsDynamicLineInfoDao.deleteById(lineInfoVo.getDynamicLineInfoId());
//				}
//			}
			if(dynamicLineVo.getDynamicLineId() != null) {
				if(logisticsDynamicLineDao.updateById(dynamicLineVo)) {
					logisticsDynamicLineInfoDao.deleteByParentId(dynamicLineVo.getDynamicLineId());//删除原有纪录
					for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineInfoVos) {
//						if(StringUtil.isEmpty(lineInfoVo.getDynamicLineInfoId())) {
							lineInfoVo.setDynamicLineId(dynamicLineVo.getDynamicLineId());
							logisticsDynamicLineInfoDao.insert(lineInfoVo);
//						}else {
//							logisticsDynamicLineInfoDao.updateById(lineInfoVo);
//						}
					}
					result.setOK(ResultCode.CODE_STATE_200, "保存成功");
				}else {
					result.setError("系统出错，保存失败");
					throw new RuntimeException();
				}
			}else {
				if(logisticsDynamicLineDao.insert(dynamicLineVo)) {
					for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineInfoVos) {
//						if(StringUtil.isEmpty(lineInfoVo.getDynamicLineInfoId())) {
							lineInfoVo.setDynamicLineId(dynamicLineVo.getDynamicLineId());
							logisticsDynamicLineInfoDao.insert(lineInfoVo);
//						}else {
//							logisticsDynamicLineInfoDao.updateById(lineInfoVo);
//						}
						
					}
					result.setOK(ResultCode.CODE_STATE_200, "保存成功");
				}else {
					result.setError("系统出错，保存失败");
					throw new RuntimeException();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统出错，保存失败");
			throw new RuntimeException();
		}
		return result;
	}

}
