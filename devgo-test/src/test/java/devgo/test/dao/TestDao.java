package devgo.test.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import devgo.framework.core.dao.EntityJpaDao;
import devgo.test.model.TestModel;

@Repository
public interface TestDao extends EntityJpaDao<TestModel> {
	@Query(value="update TestModel set name='aaaaaa'")
	@Modifying
	public void updateName();
}
