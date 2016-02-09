package devgo.framework.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import devgo.framework.core.suport.DatabaseType;
import devgo.framework.core.suport.DynamicSpecification;
import devgo.framework.core.suport.PageResult;
import devgo.framework.core.suport.SortSchema;

public interface EntityDao<Entity> {
	
	public void insert(Entity entity);
	public void delete(Serializable id);
	public void delete(Entity entity);
	public void update(Entity entity);
	public Entity get(Serializable id);
	public List<Entity> getAll();
	/**
	 * 分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageResult<Entity> paging(int pageNumber,int pageSize);
	/**
	 * 排序的分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param sortSchema
	 * @return
	 */
	public PageResult<Entity> paging(int pageNumber, int pageSize,SortSchema sortSchema);
	/**
	 * 多条件的分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param dynamicSpecifications
	 * @return
	 */
	public PageResult<Entity> paging(int pageNumber, int pageSize,List<DynamicSpecification> dynamicSpecifications);
	/**
	 * 多条件且排序的分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param dynamicSpecifications
	 * @param sortSchema
	 * @return
	 */
	public PageResult<Entity> paging(int pageNumber,int pageSize,final List<DynamicSpecification> dynamicSpecifications,SortSchema sortSchema);
	/**
	 * 获取数据库类型
	 * @return
	 */
	public DatabaseType getDatabaseType();
	/**
	 * 执行原生SQL语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> executeNativeSQL(String sql,Map<String,Object> params);
	/**
	 * 执行原生SQL语句
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	public List<Entity> executeNativeSQL(String sql,Map<String,Object> params,Class<Entity> clazz);
	/**
	 * 执行原生SQL语句，分页
	 * @param sql
	 * @param params
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public List<Object[]> executeNativeSQL(String sql,Map<String,Object> params,int pageSize,int pageNumber);
	/**
	 * 执行原生SQL语句，分页
	 * @param sql
	 * @param params
	 * @param clazz
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public List<Entity> executeNativeSQL(String sql,Map<String,Object> params,Class<Entity> clazz,int pageSize,int pageNumber);
	
}
