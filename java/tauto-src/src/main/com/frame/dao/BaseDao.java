package main.com.frame.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import main.com.frame.annotation.LogPoint;
import main.com.frame.domain.Result;
import main.com.frame.search.BaseSearch;


/**
 * 基本的CRUD操作封装
 * @author Administrator
 *
 * @param <T>
 * @param <S>
 */
public interface BaseDao<T extends Serializable> {

//	/**
//	 * 组合查询列表
//	 * @param search
//	 * @return
//	 */
//	public <V extends T> List<V> select(S search);
//	
	/**
	 * 组合查询列表
	 * @param search
	 * @return
	 */
	public <V extends T> List<V> select(Map<String,Object> params);
	
	/**
	 * 查询连带关系组合
	 * @param params
	 * @return
	 */
	public <V extends T> List<V> selectJoin(Map<String,Object> params);
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V selectById(Integer id);
	
	/**
	 * 根据ID自定义查找
	 * @param id
	 * @param sqlSelectName
	 * @return
	 */
	public <V extends T> V selectById(Integer id,String sqlSelectName);
	
	/**
	 * 根据ID获取组合关系
	 * @param id
	 * 
	 * @return
	 */
	public <V extends T> V selectByIdJoin(Integer id);
	
	/**
	 * 根据id获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V selectByUserId(Integer id);
	
	/**
	 * 根据code获取实体对象
	 * @param id
	 * @return
	 */
	public <V extends T> V selectByCode(String code);
	
	/**
	 * 插入一条记录
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Boolean insert(T entity);
	
	/**
	 * 插入一条记录
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Result insertAndResult(T entity,Result result);
	/**
	 * 插入一条记录
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Result insertAndResultIT(T entity,Result result);
	
	/**
	 * 更新一条记录的一个字段或所有字段
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Boolean updateById(T entity);
	/**
	 * 更新一条记录的一个字段或所有字段
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Result updateByIdAndResult(T entity,Result result);
	/**
	 * 更新一条记录的一个字段或所有字段
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Result updateByIdAndResultIT(T entity,Result result);
	
	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 */
//	@LogPoint
	public Boolean deleteById(Integer id);
	
//	/**
//	 * 根据组合条件删除记录
//	 * @param search
//	 * @return
//	 */
//	@LogPoint
//	public Boolean delete(S search);
	
//	/**
//	 * 根据条件获取返回记录行数
//	 * @param search
//	 * @return
//	 */
//	public Long getRowCount(S search);
	
	/**
	 * 根据条件获取返回记录行数
	 * @param search
	 * @return
	 */
	public Long getRowCount(Map<String,Object> params);
	
	/**
	 * 根据条件获取返回记录行数
	 * @param search
	 * @return
	 */
	public Long getRowCountJoin(Map<String,Object> params);
	
	/**
	 * 批量添加
	 * @param search
	 * @return
	 */
//	@LogPoint
	public Boolean batchInsert(List<T> list);
	
	/**
	 * 批量更新记录
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Boolean batchUpdate(List<T> list);
	
	/**
	 * 批量更新记录
	 * @param entity
	 * @return
	 */
//	@LogPoint
	public Boolean batchUpdateByOne(List<T> list);
}
