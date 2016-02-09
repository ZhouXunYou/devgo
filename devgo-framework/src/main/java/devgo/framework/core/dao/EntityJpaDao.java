package devgo.framework.core.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface EntityJpaDao<Entity> extends JpaRepository<Entity, Serializable>,EntityDao<Entity> {
	
}
