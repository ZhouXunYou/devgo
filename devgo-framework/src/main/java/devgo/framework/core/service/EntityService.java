package devgo.framework.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import devgo.framework.core.suport.DynamicSpecification;
import devgo.framework.core.suport.PageResult;
import devgo.framework.core.suport.SortSchema;

public interface EntityService<Entity> {
	void added(Entity entity);
	Entity findById(Serializable id);
	void modify(Entity entity);
	void remove(Entity entity);
	void remove(Serializable id);
	List<Entity> findAll();
	PageResult<Entity> pagingSearch(int pageNumber,int pageSize);
	PageResult<Entity> pagingSearch(int pageNumber, int pageSize,SortSchema sortSchema);
	PageResult<Entity> pagingSearch(int pageNumber, int pageSize,List<DynamicSpecification> dynamicSpecifications);
	PageResult<Entity> pagingSearch(int pageNumber,int pageSize,final List<DynamicSpecification> dynamicSpecifications,SortSchema sortSchema);
	public List<Object[]> findByNativeSQL(String sql,Map<String,Object> params);
	public List<Entity> findByNativeSQL(String sql,Map<String,Object> params,Class<Entity> clazz);
	public List<Object[]> findByNativeSQL(String sql,Map<String,Object> params,int pageSize,int pageNumber);
	public List<Entity> findByNativeSQL(String sql,Map<String,Object> params,Class<Entity> clazz,int pageSize,int pageNumber);
}
