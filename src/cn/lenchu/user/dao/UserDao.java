package cn.lenchu.user.dao;

import cn.lenchu.domain.User;

public interface UserDao {
	
	public User query(User user);
	
	public boolean add(User user);
	
	public User update(User user);
	
	public boolean delete(User user);

}
