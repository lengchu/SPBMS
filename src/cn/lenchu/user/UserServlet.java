package cn.lenchu.user;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.lenchu.domain.User;
import cn.lenchu.user.service.UserService;
import cn.lenchu.user.service.impl.UserServiceImpl;
import cn.lenchu.utils.BaseServlet;
import cn.lenchu.utils.PasswordUtil;
import cn.lenchu.utils.SpbmsUtils;

@SuppressWarnings("serial")
public class UserServlet extends BaseServlet {
	
	private UserService service = new UserServiceImpl();

	@Override
	public Object execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return null;
	}
	
	public Object login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		String orgName = req.getParameter("username");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
//		System.out.println(username);
//		String verifycode;
		
		Map<String, String> ret = new HashMap<String, String>();
		if (username == null || password == null || "".equals(username) || "".equals(password)) {
			ret.put("statu", "0");
			ret.put("error", "用户名或密码不能为空");
			ret.put("msg", "登录失败");
		} else {
			User user = new User();
			user.setUsername(username);
			user.setPassword(PasswordUtil.encodePwdWithMD5(password));
			User u = service.query(user);
			if(u == null) {
				ret.put("statu", "0");
				ret.put("error", "用户名或密码错误");
				ret.put("msg", "登录失败");
			} else {
				req.getSession().setAttribute("user", u);
				ret.put("statu", "1");
				ret.put("msg", "登录成功");
				ret.put("user", u.getUsername());
			}
		}
		return ret;
	}
	
	public Object reg(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String username = req.getParameter("username"); 
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String createtime = SpbmsUtils.date();
		User user = new User(username, PasswordUtil.encodePwdWithMD5(password), email, createtime);
		Map<String, String> ret = new HashMap<String, String>();
		if(service.add(user)) {
			ret.put("statu", "1");
			ret.put("msg", "注册成功");
		} else {
			ret.put("statu", "0");
			ret.put("msg", "注册失败");
		}
		return ret;
	}
	
	public Object update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String username = ((User)req.getSession().getAttribute("user")).getUsername();
		String originalPassword = req.getParameter("originalPassword");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		User u = service.query(new User(username, PasswordUtil.encodePwdWithMD5(originalPassword), null, null));
		Map<String, String> ret = new HashMap<String, String>();
		if (u != null) {
			if (email != null) {
				u.setEmail(email);
			}
			if (password != null && !"".equals(password)) {
				u.setPassword(PasswordUtil.encodePwdWithMD5(password));
			}
			service.update(u);
			req.getSession().setAttribute("user", u);
			ret.put("statu", "1");
			ret.put("msg", "修改成功");
		} else {
			ret.put("statu", "0");
			ret.put("msg", "修改失败");
			ret.put("error", "原始密码错误");
		}
		return ret;
	}
	
	public Object delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String username = ((User)req.getSession().getAttribute("user")).getUsername();
		String password = req.getParameter("password");
		Map<String, String> ret = new HashMap<String, String>();
		if (username == null || password == null) {
			ret.put("statu", "0");
			ret.put("msg", "删除失败");
			ret.put("error", "密码错误");
		} else {
			User user = new User();
			user.setUsername(username);
			user.setPassword(PasswordUtil.encodePwdWithMD5(password));
			User u = service.query(user);
			if(u == null) {
				ret.put("statu", "0");
				ret.put("msg", "删除失败");
				ret.put("error", "密码错误");
			} else {
				service.delete(u);
				ret.put("statu", "1");
				ret.put("msg", "删除成功");
			}
		}
		return ret;
	}
	
	public Object getLoginedUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		return user;
	}

	public Object logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession().removeAttribute("user");
		return null;
	}
}
