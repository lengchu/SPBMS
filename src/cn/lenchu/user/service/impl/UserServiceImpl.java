package cn.lenchu.user.service.impl;

import cn.lenchu.domain.User;
import cn.lenchu.user.dao.UserDao;
import cn.lenchu.user.dao.impl.UserDaoImpl;
import cn.lenchu.user.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User query(User user) {
		return userDao.query(user);
	}

	@Override
	public boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean delete(User user) {
		return userDao.delete(user);
	}

}
