package main.com.weixinApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.exception.BusinessException;
import main.com.frame.constants.ConsumerOrderCarAuditState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.vo.StockCarVo;
import main.com.utils.ConvertUtil;
import main.com.weixinApp.dao.dao.ConsumerOrderCarDao;
import main.com.weixinApp.dao.search.ConsumerOrderCarCreateOrUpdateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarListSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUpdateStateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadCertificationPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadCiPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadExpressPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadOtherPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadTciPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadTicketPicSearch;
import main.com.weixinApp.dao.search.UploadCheckCarPicSearch;
import main.com.weixinApp.dao.vo.ConsumerOrderCarVO;
import main.com.weixinApp.service.ConsumerOrderCarService;
@Service
public class ConsumerOrderCarServiceImpl extends BaseServiceImpl<ConsumerOrderCar> implements ConsumerOrderCarService{

	@Autowired
	private ConsumerOrderCarDao consumerOrderCarDao;
	
	@Autowired
	private StockCarDao stockCarDao;
	
	@Override
	protected BaseDao<ConsumerOrderCar> getBaseDao() {
		// TODO Auto-generated method stub
		return consumerOrderCarDao;
	}
	
	@Override
	public Result uploadCheckCarPic(UploadCheckCarPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		car.setCheckCarPic(search.getCheckCarPic());
		car.setAuditState(ConsumerOrderCarAuditState.Audited.getCode());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result uploadTicketPic(ConsumerOrderCarUploadTicketPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		
		car.setTicketPic(search.getTicketPic());
		car.setTicketRemark(search.getTicketRemark());
		car.setCertificationPic(search.getCertificationPic());
		car.setTciPic(search.getTciPic());
		car.setCiPic(search.getCiPic());
		car.setExpressPic(search.getExpressPic());
		car.setOtherPic(search.getOtherPic());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result uploadCertificatonPic(ConsumerOrderCarUploadCertificationPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		car.setCertificationPic(search.getCertificationPic());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result uploadTciPic(ConsumerOrderCarUploadTciPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		car.setTciPic(search.getTciPic());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result uploadCiPic(ConsumerOrderCarUploadCiPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		car.setCiPic(search.getCiPic());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result uploadExpressPic(ConsumerOrderCarUploadExpressPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		car.setExpressPic(search.getExpressPic());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result uploadOtherPic(ConsumerOrderCarUploadOtherPicSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		car.setOtherPic(search.getOtherPic());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	@Override
	public Result createOrUpdate(ConsumerOrderCarCreateOrUpdateSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = new ConsumerOrderCar();
		ConvertUtil.objectToObject(search, car);
		if(search.getId() == null){//新增
			car.setAuditState(ConsumerOrderCarAuditState.Auditing.getCode());
			if(!consumerOrderCarDao.insert(car)) {
				return new Result(ResultCode.CODE_STATE_4006,false,"新增失败");
			}
		}else {//更新
			if(!consumerOrderCarDao.updateById(car)) {
				return new Result(ResultCode.CODE_STATE_4006,false,"更新失败");
			}
		}		
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result list(ConsumerOrderCarListSearch search) {
		// TODO Auto-generated method stub
		Map<String,Object> map = ConvertUtil.objectToMap(search);
		map.put("isDel", 0);
		List<ConsumerOrderCar> cars = consumerOrderCarDao.select(map);
		List<ConsumerOrderCarVO> carVOs = new ArrayList<>(cars.size());
		ConvertUtil.listObjectToListObject(cars, carVOs, ConsumerOrderCarVO.class);
		for(ConsumerOrderCarVO carVO : carVOs) {
			if(carVO.getStockCarId() != null) {
				StockCar stockCar = stockCarDao.selectById(carVO.getStockCarId());
				StockCarVo stockCarVO = new StockCarVo();
				ConvertUtil.objectToObject(stockCar, stockCarVO);
				carVO.setStockCar(stockCarVO);
			}
			
		}		
		List<ConsumerOrderCarVO> returnCarVOs = 
				carVOs.stream().filter((c) -> !Objects.equals(c.getAuditState(), ConsumerOrderCarAuditState.Auditing.getCode()))
				.collect(Collectors.toList());
		return new Result(returnCarVOs);
	}
	
	@Override
	@Transactional
	public Result updateState(ConsumerOrderCarUpdateStateSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = new ConsumerOrderCar();
		car.setId(search.getId());
		car.setAuditState(search.getAuditState());
		car.setAuditRemark(search.getAuditRemark());
		consumerOrderCarDao.updateById(car);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	
}
