package cn.lenchu.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {
	
	Gson gson = new Gson();

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String methodName = req.getParameter("action");
		if ("".equals(methodName) || methodName == null) {
			methodName = "execute";
		}
		
		try {
			Class<? extends BaseServlet> clazz = this.getClass();
			Method method = clazz.getDeclaredMethod(methodName, 
					HttpServletRequest.class, HttpServletResponse.class);
			resp.setContentType("text/html;charset=UTF-8");
			Object json = method.invoke(this, req, resp);
			if (json != null) {
				resp.getWriter().write(gson.toJson(json));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected abstract Object execute(HttpServletRequest req, 
			HttpServletResponse resp) throws Exception;
}
