package devgo.framework.core.dao.impl;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.jboss.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import devgo.framework.core.dao.EntityJpaDao;
import devgo.framework.core.suport.DatabaseType;
import devgo.framework.core.suport.DynamicSpecification;
import devgo.framework.core.suport.PageResult;
import devgo.framework.core.suport.SortSchema;
@NoRepositoryBean
public class EntityJpaDaoImpl<Entity> extends SimpleJpaRepository<Entity, Serializable> implements EntityJpaDao<Entity>   {
	
	private static final Logger LOG = Logger.getLogger(EntityJpaDaoImpl.class);
	
	private JpaEntityInformation<Entity, ?> jpaEntityInformation;
	private EntityManager entityManager;
	private DatabaseType databaseType;
	public EntityJpaDaoImpl(JpaEntityInformation<Entity, ?> entityInformation, EntityManager entityManager) {
		
		super(entityInformation, entityManager);
		LOG.debug("Construction"+entityInformation.getEntityName()+" Object");
		this.jpaEntityInformation = entityInformation;
		this.entityManager = entityManager;
		Session session = (Session)this.entityManager.getDelegate();
		String databaseName = ((SessionImpl)session).getFactory().getDialect().getClass().getName().toUpperCase();
		if(databaseName.indexOf("MYSQL")!=-1){
			databaseType = DatabaseType.MySQL;
		}else if(databaseName.indexOf("POSTGRESQL")!=-1){
			databaseType = DatabaseType.PostgreSQL;
		}else if(databaseName.indexOf("ORACLE")!=-1){
			databaseType = DatabaseType.Oracle;
		}else if(databaseName.indexOf("SQLSERVER")!=-1){
			databaseType = DatabaseType.SQLServer;
		}else if(databaseName.indexOf("DB2")!=-1){
			databaseType = DatabaseType.DB2;
		}
		LOG.debug("Object"+entityInformation.getEntityName()+" use "+databaseType.name()+" database");
	}
	
	public JpaEntityInformation<Entity, ?> getJpaEntityInformation() {
		return jpaEntityInformation;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 构建分页对象
	 * @param pageNumber
	 * @param pageSize
	 * @param sortSchema
	 * @return
	 */
	private Pageable buildPageable(int pageNumber, int pageSize,SortSchema sortSchema){
		LOG.debug("Build Pageable Start");
		pageNumber = pageNumber<=0?1:pageNumber;
		LOG.debug("Pageable page number is " + pageNumber);
		LOG.debug("Pageable page size is " + pageSize);
		Pageable page;
		if(sortSchema!=null&&sortSchema.getSchema().size()>0){
			Order[] orders = new Order[sortSchema.getSchema().size()];
			int index = 0;
			for(Map.Entry<String,Boolean> entry:sortSchema.getSchema().entrySet()){
				Direction direction;
				if(entry.getValue()){
					direction = Direction.ASC;
				}else{
					direction = Direction.DESC;
				}
				orders[index] = new Order(direction,entry.getKey());
				LOG.debug("order by "+entry.getKey()+" "+direction.name());
				index++;
			}
			Sort sort = new Sort(orders);
			page = new PageRequest(pageNumber - 1, pageSize,sort);
		}else{
			page = new PageRequest(pageNumber - 1, pageSize);
		}
		LOG.debug("Build Pageable Finished");
		return page;
	}
	private PageResult<Entity> buildPageResult(Page<Entity> page){
		LOG.debug("Build PageResult Object Start");
		PageResult<Entity> result = new PageResult<Entity>();
		if(page!=null){
			result.setCurrentResultCount(page.getNumberOfElements());
			result.setFirst(page.isFirst());
			result.setLast(page.isLast());
			result.setResultSet(page.getContent());
			result.setTotalPageCount(page.getTotalPages());
			result.setTotalResultCount(page.getTotalElements());
			result.setContent(page.hasContent());
			result.setNext(page.hasNext());
			result.setPrevious(page.hasPrevious());
			result.setPageNumber(page.getNumber());
			result.setPageSize(page.getSize());
		}
		LOG.debug("Build PageResult Finished");
		return result;
	}
	@Override
	public void insert(Entity entity) {
		LOG.debug("Save Entity " + entity.getClass().getName());
		super.save(entity);
	}

	@Override
	public void update(Entity entity) {
		LOG.debug("Update Entity " + entity.getClass().getName());
		super.save(entity);
	}

	@Override
	public Entity get(Serializable id) {
		LOG.debug("Get Entity by Id:" + id);
		return super.findOne(id);
	}

	@Override
	public List<Entity> getAll() {
		LOG.debug("Get All Entity");
		return super.findAll();
	}

	@Override
	public PageResult<Entity> paging(int pageNumber,int pageSize) {
		SortSchema sortSchema = null;
		return paging(pageNumber,pageSize,sortSchema);
	}
	

	@Override
	public PageResult<Entity> paging(int pageNumber, int pageSize, SortSchema sortSchema) {
		Pageable pageable = buildPageable(pageNumber,pageSize,sortSchema);
		Page<Entity> page = super.findAll(pageable);
		return buildPageResult(page);
	}

	@Override
	public PageResult<Entity> paging(int pageNumber, int pageSize,	List<DynamicSpecification> dynamicSpecifications) {
		return paging(pageNumber, pageSize,	 dynamicSpecifications,null);
	}

	@Override
	public PageResult<Entity> paging(int pageNumber, int pageSize, final List<DynamicSpecification> dynamicSpecifications, SortSchema sortSchema) {
		LOG.debug("Paging Query Entity");
		Pageable pageable = buildPageable(pageNumber, pageSize, sortSchema);
		
		Page<Entity> page = findAll(new Specification<Entity>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(dynamicSpecifications!=null&&dynamicSpecifications.size()>0){
					List<Predicate> predicates = new ArrayList<Predicate>();
					for(DynamicSpecification dynamicSpecification:dynamicSpecifications){
						String[] names = dynamicSpecification.getName().split(".");
						Path<Object> path = root.get(dynamicSpecification.getName());
						for(int i=1;i<names.length;i++){
							path = path.get(names[i]);
						}
//						dynamicSpecification.setValue(conversion.convert(dynamicSpecification.getValue(), root.get(dynamicSpecification.getName()).getJavaType()));
//						Class<Integer> clazz = (Class<Integer>)getObjectTypeClass(root.get(dynamicSpecification.getName()).getJavaType());
						switch (dynamicSpecification.getOperator()) {
						case EQ:
							predicates.add(cb.equal(path, dynamicSpecification.getValue()));
							break;
						case NOTEQ:
							predicates.add(cb.notEqual(path, dynamicSpecification.getValue()));
							break;
						case LIKE:
							predicates.add(cb.like(path.as(String.class), "%"+dynamicSpecification.getValue()+"%"));
							break;
						case NOTLIKE:
							predicates.add(cb.notLike(path.as(String.class), "%"+dynamicSpecification.getValue()+"%"));
							break;
						case LLIKE:
							predicates.add(cb.like(path.as(String.class), dynamicSpecification.getValue()+"%"));
							break;
						case RLIKE:
							predicates.add(cb.like(path.as(String.class), "%"+dynamicSpecification.getValue()));
							break;
						case GT:
							predicates.add(cb.greaterThan(path.as(getObjectTypeClass(path.getJavaType())), (Comparable)dynamicSpecification.getValue()));
							break;
						case LT:
							predicates.add(cb.lessThan(path.as(getObjectTypeClass(path.getJavaType())), (Comparable)dynamicSpecification.getValue()));
							break;
						case GTE:
							predicates.add(cb.greaterThanOrEqualTo(path.as(getObjectTypeClass(path.getJavaType())), (Comparable)dynamicSpecification.getValue()));
							break;
						case LTE:
							predicates.add(cb.lessThanOrEqualTo(path.as(getObjectTypeClass(path.getJavaType())), (Comparable)dynamicSpecification.getValue()));
							break;
						case NULL:
							predicates.add(cb.isNull(path));
							break;
						case NOTNULL:
							predicates.add(cb.isNotNull(path));
							break;
						case BETWEEN:
							Object[] values = (Object[])dynamicSpecification.getValue();
							predicates.add(cb.between(path.as(getObjectTypeClass(path.getJavaType())), (Comparable)values[0], (Comparable)values[1]));
						}
					}
					if(!predicates.isEmpty()){
						LOG.debug("Build Page Object Success");
						return cb.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				LOG.debug("Page Object is null");
				return null;
			}
			/**
			 * 奖基础数据类型转换为对象数据类型（包含日期类型）
			 * @param baseDataType
			 * @return
			 */
			private Class<? extends Comparable<?>> getObjectTypeClass(Class<?> baseDataType){
				if(baseDataType == int.class){
					return Integer.class;
				}else if(baseDataType == double.class){
					return Double.class;
				}else if(baseDataType == Date.class){
					return Date.class;
				}else if(baseDataType == long.class){
					return Long.class;
				}else if(baseDataType == char.class){
					return Character.class;
				}else if(baseDataType == boolean.class){
					return Boolean.class;
				}else if(baseDataType == float.class){
					return Float.class;
				}else if(baseDataType == byte.class){
					return Byte.class;
				}else if(baseDataType == short.class){
					return Short.class;
				}
				throw new RuntimeException("data type error");
			}
		}, pageable);
		return buildPageResult(page);
	}


	@Override
	public DatabaseType getDatabaseType() {
		return this.databaseType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeNativeSQL(String sql, Map<String, Object> params) {
		LOG.debug("Execute native SQL");
		Query query = getEntityManager().createNativeQuery(sql);
		return (List<Object[]>)executeNativeSQL(query, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> executeNativeSQL(String sql, Map<String, Object> params, Class<Entity> clazz) {
		LOG.debug("Execute native SQL return List<"+clazz.getName()+">");
		Query query = getEntityManager().createNativeQuery(sql,clazz);
		return (List<Entity>)executeNativeSQL(query, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeNativeSQL(String sql, Map<String, Object> params, int pageSize, int pageNumber) {
		LOG.debug("Execute native SQL page size is "+pageSize+",page number is "+pageNumber);
		Query query = getEntityManager().createNativeQuery(sql).setFirstResult(pageNumber*pageSize).setMaxResults(pageNumber*pageSize+pageSize);
		return (List<Object[]>)executeNativeSQL(query, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> executeNativeSQL(String sql, Map<String, Object> params, Class<Entity> clazz, int pageSize, int pageNumber) {
		LOG.debug("Execute native SQL page size is "+pageSize+", page number is "+pageNumber+", return List<"+clazz.getName()+">");
		Query query = getEntityManager().createNativeQuery(sql,clazz).setFirstResult(pageNumber*pageSize).setMaxResults(pageNumber*pageSize+pageSize);
		return (List<Entity>)executeNativeSQL(query, params);
	}
	private List<?> executeNativeSQL(Query query,Map<String, Object> params){
		if(params!=null&&params.size()>0){
			LOG.debug("have params");
			for(Map.Entry<String, Object> entry:params.entrySet()){
				String key = entry.getKey();
				Object value = entry.getValue();
				LOG.debug(key+":"+value);
				query.setParameter(key, value);
			}
		}
		return query.getResultList();
	}
	
	
	
	
}
