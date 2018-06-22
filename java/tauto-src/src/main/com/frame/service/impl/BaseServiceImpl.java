package main.com.frame.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.com.frame.base.Page;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.search.BaseSearch;
import main.com.frame.service.BaseService;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.po.ShopFindCar;

/**
 * 基础Service服务接口实现类
 */
public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	/**
	 * 获取基础数据库操作类
	 * @return
	 */
	protected abstract BaseDao<T> getBaseDao();
	
//	/**
//	 * 组合查询列表
//	 * @param search
//	 * @return
//	 */
//	public <V extends T> List<V> getList(S search){
//		return this.getBaseDao().select(search);
//	}
	
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V getById(Integer id){
		return this.getBaseDao().selectById(id);
	}
	
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V getByIdJoin(Integer id){
		return this.getBaseDao().selectByIdJoin(id);
	}
	
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V getByCode(String code){
		return this.getBaseDao().selectByCode(code);
	}
	
	/**
	 * 插入一条记录
	 * @param entity
	 * @return
	 */
	public Boolean add(T entity){
		return this.getBaseDao().insert(entity);
	}
	
	/**
	 * 更新一条记录的一个字段或所有字段
	 * @param entity
	 * @return
	 */
	public Boolean edit(T entity){
		return this.getBaseDao().updateById(entity);
	}
	
	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 */
	public Boolean deleteById(Integer id){
		return this.getBaseDao().deleteById(id);
	}
	
//	/**
//	 * 根据组合条件删除记录
//	 * @param search
//	 * @return
//	 */
//	public Boolean delete(S search){
//		return this.getBaseDao().delete(search);
//	}
	

//	/**
//	 * 根据条件获取返回记录行数
//	 * @param search
//	 * @return
//	 */
//	public Long getRowCount(S search){
//		return this.getBaseDao().getRowCount(search);
//	}
	
//	public <V extends T> Page<V> getPage(S search){
//		Page<V> page = new Page<V>();
//		List<V> list = this.getBaseDao().select(search);
//		page.setPage(search.getPage());
//		page.setRows(search.getRows());
//		page.setTotal(this.getBaseDao().getRowCount(search));
//		page.setRowsObject(list);
//		return page;
//	}
//	
	/**
	 * 批量插入记录
	 * @param entity
	 * @return
	 */
	public Boolean batchAdd(List<T> list){
		return this.getBaseDao().batchInsert(list);
	}
	
	/**
	 * 批量更新记录
	 * @param entity
	 * @return
	 */
	public Boolean batchUpdate(List<T> list){
		return this.getBaseDao().batchUpdate(list);
	}
	
	public T save(T t,Integer id) throws Exception{
		if(t == null) {
			return null;
		}else if(StringUtil.isEmpty(id)) {
			if(getBaseDao().insert(t)) {
				return t;
			}else {
				return null;
			}
		}else {
			if(getBaseDao().updateById(t)) {
				return t;
			}else {
				return null;
			}
		}
	}
	
	public Result save(T t,Result result,Integer id) throws Exception{
		if(t == null) {
			result.setError("保存数据错误");
			return result;
		}else if(StringUtil.isEmpty(id)) {
			if(getBaseDao().insert(t)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}else {
			if(getBaseDao().updateById(t)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}
	}

	/**
	 * 手动查询参数凭接，自动装换会把所有类型全部变成String，所以使用手动拼接
	 * @param search
	 * @return
	 */
//	public Map<String, Object> getSearchParams(S search){
//		Map<String,Object> params = new HashMap<String, Object>();//查询参数集合;
//		//按那个属性排序
//		if(search.getOrder() != null && !"".equals(search.getOrder())){
//			//实体的属性名字与数据库名字不一致时，在这里处理即可，或者在页面将该字段属性设置为：sortable:false;
//			params.put("sortField", search.getSort());
//			//升序或降序
//			params.put("sortType", search.getOrder());
//		}
//		if(search.getPage()!=0 && search.getIsPage()){
//			//从第几条开始
//			params.put("offset", (search.getPage()-1)*search.getRows());
//			//返回几条
//			params.put("limit", search.getRows());
//		}
//		return params;
//	}
}
