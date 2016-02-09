package devgo.test1.service.impl;

import org.springframework.stereotype.Service;

import devgo.framework.core.service.impl.EntityServiceImpl;
import devgo.test1.dao.UserDao;
import devgo.test1.model.User;
import devgo.test1.service.UserService;

@Service
public class UserServiceImpl extends EntityServiceImpl<User, UserDao> implements UserService {

}
