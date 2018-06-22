package main.com.logistics.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import main.com.frame.domain.Result;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LoadCarSearch;
import main.com.logistics.dao.search.MakeCarArrivedSearch;
import main.com.logistics.dao.search.SignCarSearch;
import main.com.logistics.dao.search.UnloadCarSearch;
import main.com.logistics.dao.search.UpdateDistributionStateSearch;
import main.com.logistics.dao.search.UploadPicSearch;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.system.dao.search.SystemUsersSearch;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月15日 下午5:06:11 
* 类描述： 物流司机模块service
*/
public interface LogisticsDriverService {
	
	/**
	 * 登录
	 * @param search
	 * @param id
	 * @param result 
	 * @param request 
	 * @return
	 */
	Result login(SystemUsersSearch search,Result result,HttpSession session)throws Exception;

	/**
	 * 用户退出
	 * @param search 
	 * @param search
	 * @param id
	 * @param result
	 */
	Result loginOut(SystemUsersSearch search, Result result)throws Exception;

	/**
	 * 修改密码
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result changePassword(SystemUsersSearch search, Result result,HttpSession session)throws Exception;

	 /**
     * 查询配送中订单
     * @param search
     * @return
     */
    Result queryDriverDistributionDetails(DriverDistributionSearch search) throws Exception;
    
    /**
     * 根据货物车ID上传图片
     * @param search
     * @return
     */
    Result uploadPic(UploadPicSearch search);
    
    /**
     * 开始配送
     * date: 2018年1月17日 上午11:04:02
     * @param distributionId
     * @return
     */
    Result startDelivery(Integer distributionId) throws Exception;
    
    /**
     * 配送完成
     * date: 2018年1月17日 上午11:27:43
     * @param distributionId
     * @return
     * @throws Exception
     */
    Result endDelivary(Integer distributionId) throws Exception;
    
    /**
	 * 获取物流单列表
	 * @return
	 */
	Result listLogisticsDistribution(String keywords);
	
	/**
	 * 更新物流单状态
	 * @param search
	 * @return
	 */
	Result updateDistributionState(UpdateDistributionStateSearch search);
	
	/**
	 * 装车
	 * @param search
	 * @return
	 */
	Result loadCar(LoadCarSearch search);
	
	/**
	 * 到达目的地
	 * @return
	 */
	Result makeCarArrived(MakeCarArrivedSearch search);
	
	/**
	 * 卸车
	 * @param search
	 * @return
	 */
	Result unloadCar(LoadCarSearch search);
	
	/**
	 * 签收
	 * @param search
	 * @return
	 */
	Result signCar(SignCarSearch search);

	/**
	 * 根据sessionId查询登陆
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	LogisticsDriverVo getByCode(String sessionId)throws Exception;
}
