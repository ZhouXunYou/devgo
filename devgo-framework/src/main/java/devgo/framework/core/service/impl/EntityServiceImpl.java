package devgo.framework.core.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import devgo.framework.core.dao.EntityDao;
import devgo.framework.core.service.EntityService;
import devgo.framework.core.suport.DynamicSpecification;
import devgo.framework.core.suport.PageResult;
import devgo.framework.core.suport.SortSchema;

public abstract class EntityServiceImpl<Entity,Dao extends EntityDao<Entity>> implements EntityService<Entity>,ApplicationContextAware {
	private static final Logger LOG = Logger.getLogger(EntityServiceImpl.class);
	private ApplicationContext applicationContext;
	private Dao entityDao;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)	throws BeansException {
		this.applicationContext = applicationContext;
	}
	private Class<?> getEntityDaoClass(){
		LOG.debug("get entity dao class");
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		Class<?> classType = (Class<?>)params[1];
		LOG.debug("entity dao class type is "+classType.getName());
		return classType;
		
	}
	@SuppressWarnings("unchecked")
	protected Dao getEntityDao(){
		if(this.entityDao==null){
			this.entityDao = (Dao)this.applicationContext.getBean(getEntityDaoClass());
			LOG.debug("get bean "+this.entityDao.getClass().getName());
		}
		return this.entityDao;
	}
	@Override
	public void added(Entity entity) {
		LOG.debug("added entity "+entity.getClass().getName());
		getEntityDao().insert(entity);
	}
	@Override
	public Entity findById(Serializable id) {
		LOG.debug("find by id: "+id);
		return getEntityDao().get(id);
	}
	@Override
	public void modify(Entity entity) {
		LOG.debug("modify entity "+entity.getClass().getName());
		getEntityDao().update(entity);
	}
	@Override
	public void remove(Entity entity) {
		LOG.debug("remove entity "+entity.getClass().getName());
		getEntityDao().delete(entity);
	}
	@Override
	public void remove(Serializable id) {
		LOG.debug("remove entity by id: "+id);
		getEntityDao().delete(id);
	}
	@Override
	public List<Entity> findAll() {
		LOG.debug("find all");
		return getEntityDao().getAll();
	}
	@Override
	public PageResult<Entity> pagingSearch(int pageNumber, int pageSize) {
		LOG.debug("paging search");
		return getEntityDao().paging(pageNumber, pageSize);
	}
	@Override
	public PageResult<Entity> pagingSearch(int pageNumber, int pageSize,
			SortSchema sortSchema) {
		LOG.debug("paging search");
		return getEntityDao().paging(pageNumber, pageSize,sortSchema);
	}
	@Override
	public PageResult<Entity> pagingSearch(int pageNumber, int pageSize,
			List<DynamicSpecification> dynamicSpecifications) {
		LOG.debug("paging search");
		return getEntityDao().paging(pageNumber, pageSize,dynamicSpecifications);
	}
	@Override
	public PageResult<Entity> pagingSearch(int pageNumber, int pageSize,
			List<DynamicSpecification> dynamicSpecifications,
			SortSchema sortSchema) {
		LOG.debug("paging search");
		return getEntityDao().paging(pageNumber, pageSize,dynamicSpecifications,sortSchema);
	}
	@Override
	public List<Object[]> findByNativeSQL(String sql, Map<String, Object> params) {
		return getEntityDao().executeNativeSQL(sql, params);
	}
	@Override
	public List<Entity> findByNativeSQL(String sql, Map<String, Object> params, Class<Entity> clazz) {
		return getEntityDao().executeNativeSQL(sql, params, clazz);
	}
	@Override
	public List<Object[]> findByNativeSQL(String sql, Map<String, Object> params, int pageSize, int pageNumber) {
		return getEntityDao().executeNativeSQL(sql, params, pageSize, pageNumber);
	}
	@Override
	public List<Entity> findByNativeSQL(String sql, Map<String, Object> params, Class<Entity> clazz, int pageSize, int pageNumber) {
		return getEntityDao().executeNativeSQL(sql, params, clazz, pageSize, pageNumber);
	}
	
	
	
}
