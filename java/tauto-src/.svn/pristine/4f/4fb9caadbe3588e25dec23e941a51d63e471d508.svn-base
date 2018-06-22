package main.com.frame.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.exception.DaoException;
import main.com.frame.search.BaseSearch;
import main.com.utils.BeanUtils;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 基础Dao接口实现类，实现改类的子类必须设置泛型类型
 */
public abstract class BaseDaoImpl<T extends Serializable>
		implements BaseDao<T> {
	@Autowired(required = true)
	protected SqlSessionTemplate sqlSessionTemplate;

	public static final String SQLNAME_SEPARATOR = ".";

	/**
	 * @fields sqlNamespace SqlMapping命名空间
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * 获取泛型类型的实体对象类全名
	 * 
	 * @author
	 * @return
	 * @date 2014年3月3日下午6:17:46
	 */
	protected String getDefaultSqlNamespace() {
		Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
		return genericClass == null ? null : genericClass.getName();
	}

	/**
	 * 获取SqlMapping命名空间
	 * 
	 * @author
	 * @return SqlMapping命名空间
	 * @date 2014年3月4日上午12:33:15
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * 设置SqlMapping命名空间。 以改变默认的SqlMapping命名空间， 不能滥用此方法随意改变SqlMapping命名空间。
	 * 
	 * @author
	 * @param sqlNamespace
	 *            SqlMapping命名空间
	 * @date 2014年3月4日上午12:33:17
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * 
	 * @param sqlName
	 *            SqlMapping名
	 * @return 组合了SqlMapping命名空间后的完整SqlMapping名
	 */
	protected String getSqlName(String sqlName) {
//		System.out.println(sqlNamespace + SQLNAME_SEPARATOR + sqlName);
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

//	@Override
//	public <V extends T> List<V> select(S search) {
//		try {
//			Map<String, Object> params = BeanUtils.toMap(search);
//			return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT),
//					params);
//		} catch (Exception e) {
//			throw new DaoException(String.format("查询对象列表出错！语句：%s",
//					getSqlName(SqlId.SQL_SELECT)), e);
//		}
//	}
	@Override
	public <V extends T> List<V> select(Map<String,Object> params) {
		try {
//			System.out.println(getSqlName(SqlId.SQL_SELECT));
			return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT)), e);
		}
	}
	
	@Override
	public <V extends T> List<V> selectJoin(Map<String,Object> params) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_JOIN),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_JOIN)), e);
		}
	}

	public <V extends T> V selectById(Integer id) {
		try {
			if(id == null || id <= 0) {
				return null;
			}
			return sqlSessionTemplate.selectOne(
					getSqlName(SqlId.SQL_SELECT_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_BY_ID)), e);
		}
	}
	
	public <V extends T> V selectById(Integer id,String sqlSelectName) {
		try {
			if(id == null || id <= 0) {
				return null;
			}
			return sqlSessionTemplate.selectOne(
					getSqlName(sqlSelectName), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName(sqlSelectName)), e);
		}
	}
	
	public <V extends T> V selectByIdJoin(Integer id) {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName(SqlId.SQL_SELECT_BY_ID_JOIN), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_BY_ID_JOIN)), e);
		}
	}
	
	public <V extends T> V selectByUserId(Integer id) {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName(SqlId.SQL_SELECT_BY_USERID), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_BY_ID)), e);
		}
	}
	
	public <V extends T> V selectByCode(String code) {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName(SqlId.SQL_SELECT_BY_CODE), code);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_BY_CODE)), e);
		}
	}

	public synchronized Boolean insert(T entity){
		try {
			int rows = sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT),
					entity);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",
					getSqlName(SqlId.SQL_INSERT)), e);
		}
	}
	
	public synchronized Result insertAndResult(T entity,Result result) {
		try {
			int rows = sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT),
					entity);
			if (rows > 0) {
				result.setOK(ResultCode.CODE_STATE_200,"操作成功");
			}else {
				result.setErrorKefu();
			}
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",
					getSqlName(SqlId.SQL_INSERT)), e);
		}
		return result;
	}
	
	public synchronized Result insertAndResultIT(T entity,Result result) {
		try {
			int rows = sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT),
					entity);
			if (rows > 0) {
				result.setOK(ResultCode.CODE_STATE_200,"操作成功");
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "系统错误，请联系IT部");
			}
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",
					getSqlName(SqlId.SQL_INSERT)), e);
		}
		return result;
	}
	
//	public Integer add(T entity) {
//		try {
//			int rows = sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT),
//					entity);
//			return rows;
//		} catch (Exception e) {
//			throw new DaoException(String.format("添加对象出错！语句：%s",
//					getSqlName(SqlId.SQL_INSERT)), e);
//		}
//	}

	public synchronized Boolean updateById(T entity) {
		try {
			int rows = sqlSessionTemplate.update(
					getSqlName(SqlId.SQL_UPDATE_BY_ID), entity);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象出错！语句：%s",
					getSqlName(SqlId.SQL_UPDATE_BY_ID)), e);
		}
	}
	
	public synchronized Result updateByIdAndResult(T entity,Result result) {
		try {
			int rows = sqlSessionTemplate.update(
					getSqlName(SqlId.SQL_UPDATE_BY_ID), entity);
			if (rows > 0) {
				result.setOK(ResultCode.CODE_STATE_200,"操作成功");
			}else {
				result.setErrorKefu();
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象出错！语句：%s",
					getSqlName(SqlId.SQL_UPDATE_BY_ID)), e);
		}
		return result;
	}
	public synchronized Result updateByIdAndResultIT(T entity,Result result) {
		try {
			int rows = sqlSessionTemplate.update(
					getSqlName(SqlId.SQL_UPDATE_BY_ID), entity);
			if (rows > 0) {
				result.setOK(ResultCode.CODE_STATE_200,"操作成功");
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "系统错误，请联系IT部");
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象出错！语句：%s",
					getSqlName(SqlId.SQL_UPDATE_BY_ID)), e);
		}
		return result;
	}

	public synchronized Boolean deleteById(Integer id) {
		try {
			int rows = sqlSessionTemplate.delete(
					getSqlName(SqlId.SQL_DELETE_BY_ID), id);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID删除对象出错！语句：%s",
					getSqlName(SqlId.SQL_DELETE_BY_ID)), e);
		}
	}

//	public synchronized Boolean delete(S search) {
//		try {
//			Map<String, Object> params = BeanUtils.toMap(search);
//			int rows = sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE),
//					params);
//			if (rows > 0) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			throw new DaoException(String.format("删除对象出错！语句：%s",
//					getSqlName(SqlId.SQL_DELETE)), e);
//		}
//	}
	
	/**
	 * 根据条件获取返回记录行数
	 * @param search
	 * @return
	 */
//	public Long getRowCount(S search){
//		try {
//			Map<String, Object> params = BeanUtils.toMap(search);
//			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_COUNT), params);
//		} catch (Exception e) {
//			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
//		}
//	}
	
	/**
	 * 根据条件获取返回记录行数
	 * @param search
	 * @return
	 */
	public Long getRowCount(Map<String,Object> params){
		try {
//			Map<String, Object> params = BeanUtils.toMap(search);
			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_COUNT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}
	
	/**
	 * 根据条件获取返回记录行数
	 * @param search
	 * @return
	 */
	public Long getRowCountJoin(Map<String,Object> params){
		try {
//			Map<String, Object> params = BeanUtils.toMap(search);
			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_COUNT_JOIN), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}
	
	public synchronized Boolean batchInsert(List<T> list){
		try {
			int rows = sqlSessionTemplate.insert(getSqlName(SqlId.SQL_BATCHINSERT),list);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("批量添加对象出错！语句：%s",
					getSqlName(SqlId.SQL_BATCHINSERT)), e);
		}
	}
	
	public synchronized Boolean batchUpdate(List<T> list){
		try {
			int rows = sqlSessionTemplate.insert(getSqlName(SqlId.SQL_BATCHUPDATE),list);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("批量更新对象出错！语句：%s",
					getSqlName(SqlId.SQL_BATCHUPDATE)), e);
		}
	}
	
	public synchronized Boolean batchUpdateByOne(List<T> list){
		try {
			int rows = sqlSessionTemplate.insert(getSqlName("batchUpdateByOne"),list);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("批量更新对象出错！语句：%s",
					getSqlName(SqlId.SQL_BATCHUPDATE)), e);
		}
	}

}
