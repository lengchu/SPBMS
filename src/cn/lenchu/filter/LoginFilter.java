package cn.lenchu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lenchu.domain.User;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println(req.getServletPath());
		
		User user = (User) req.getSession().getAttribute("user");
		
		if (user != null) {
			chain.doFilter(req, resp);
		} else if ("/login.html".equals(req.getServletPath())) {
			chain.doFilter(req, resp);
		}else if (req.getServletPath().startsWith("/static/")) {
			chain.doFilter(req, resp);
		} else if ("/UserServlet".equals(req.getServletPath())) {
			if ("action=reg".equals(req.getQueryString()) || 
				"action=login".equals(req.getQueryString())) {
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/login.html");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.html");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
