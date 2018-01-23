package cn.lenchu.user.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import cn.lenchu.domain.User;
import cn.lenchu.user.dao.UserDao;
import cn.lenchu.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User query(User user) {
		String sql = "select * from user where username=? and password=?";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		User u = null;
		try {
			u = run.query(sql, new BeanHandler<User>(User.class),
					 user.getUsername(), user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean add(User user) {
		String sql = "insert into user(username,password,email,createtime) values(?,?,?,?)";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		int result = 0;
		try {
			result = run.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getCreatetime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result != 0;
	}

	@Override
	public User update(User user) {
		String sql = "update user set password=?,email=? where username=?";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		User u = null;
		try {
			run.update(sql, user.getPassword(), user.getEmail(), user.getUsername());
			u = user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean delete(User user) {
		String sql = "delete from user where username=?";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		int result = 0;
		try {
			result = run.update(sql, user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result != 0;
	}

}
