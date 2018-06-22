package main.com.frame.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import main.com.frame.annotation.LogPoint;
import main.com.frame.base.Page;
import main.com.frame.domain.Result;
import main.com.frame.search.BaseSearch;

/**
 * 基础的Service接口
 * @author 
 */
public interface BaseService<T extends Serializable> {
	/**
	 * 组合查询列表
	 * @param search
	 * @return
	 */
//	public <V extends T> List<V> getList(S search);
	
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V getById(Integer id);
	
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V getByIdJoin(Integer id);
	
	/**
	 * 根据code获取实体对象
	 * @param code
	 * @return
	 */
	public <V extends T> V getByCode(String code);
	
	/**
	 * 插入一条记录
	 * @param entity
	 * @return
	 */
	public Boolean add(T entity);
	
	/**
	 * 更新一条记录的一个字段或所有字段
	 * @param entity
	 * @return
	 */
	public Boolean edit(T entity);
	
	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 */
	public Boolean deleteById(Integer id);
	
//	/**
//	 * 根据条件获取返回记录行数
//	 * @param search
//	 * @return
//	 */
//	public Long getRowCount(S search);
	
	/**
	 * 获取分页对象
	 * @param search
	 * @return
	 */
//	public <V extends T> Page<V> getPage(S search);
	
	/**
	 * 批量插入记录
	 * @param entity
	 * @return
	 */
	public Boolean batchAdd(List<T> list);
	
	/**
	 * 批量更新记录
	 * @param entity
	 * @return
	 */
	public Boolean batchUpdate(List<T> list);
	
	public T save(T t,Integer id) throws Exception;
	
	public Result save(T t,Result result,Integer id) throws Exception;
	/**
	 * 手动拼接参数
	 * @param search
	 * @return
	 */
//	public Map<String, Object> getSearchParams(S search);
}