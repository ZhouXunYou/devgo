package devgo.framework.core.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import devgo.framework.core.dao.EntityJpaDao;
import devgo.framework.core.dao.impl.EntityJpaDaoImpl;

public class EntityRepositoryFactory<Entity> extends JpaRepositoryFactory {
	private EntityManager entityManager;
	public EntityRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object getTargetRepository(RepositoryMetadata metadata) {
		return new EntityJpaDaoImpl<Entity>(getEntityInformation((Class<Entity>)metadata.getDomainType()), entityManager);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return EntityJpaDao.class;
	}
	
}
