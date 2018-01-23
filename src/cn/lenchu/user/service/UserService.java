package cn.lenchu.user.service;

import cn.lenchu.domain.User;

public interface UserService {

	User query(User user);
	
	boolean add(User user);
	
	User update(User user);
	
	boolean delete(User user);
	
}
