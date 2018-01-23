package cn.lenchu.test;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.lenchu.domain.Book;
import cn.lenchu.utils.BaseServlet;

@SuppressWarnings("serial")
public class JsonPostTest extends BaseServlet {

	@Override
	public Object execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String orgiData = req.getParameter("json");
		System.out.println(URLDecoder.decode(orgiData, "UTF-8"));
		testJsonTransfer(orgiData);
		return null;
	}
	
//	@Test
	public void testJsonTransfer(String json) {
		Gson gson = new Gson();
		Book book = gson.fromJson(json, Book.class);
		System.out.println(book);
	}

}
