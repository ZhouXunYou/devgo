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
	
	public DatabaseType getDatabaseType();
	public List<Object[]> executeNativeSQL(String sql,Map<String,Object> params);
	public List<Entity> executeNativeSQL(String sql,Map<String,Object> params,Class<Entity> clazz);
	public List<Object[]> executeNativeSQL(String sql,Map<String,Object> params,int pageSize,int pageNumber);
	public List<Entity> executeNativeSQL(String sql,Map<String,Object> params,Class<Entity> clazz,int pageSize,int pageNumber);
	
}
