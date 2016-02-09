package devgo.test1.dao;

import org.springframework.stereotype.Repository;

import devgo.framework.core.dao.EntityJpaDao;
import devgo.test1.model.User;

@Repository
public interface UserDao extends EntityJpaDao<User> {
	
}
