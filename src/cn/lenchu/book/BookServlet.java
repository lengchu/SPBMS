package cn.lenchu.book;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.lenchu.book.service.BookService;
import cn.lenchu.book.service.impl.BookServiceImpl;
import cn.lenchu.domain.Book;
import cn.lenchu.domain.User;
import cn.lenchu.utils.BaseServlet;
import cn.lenchu.utils.SpbmsUtils;

@SuppressWarnings("serial")
public class BookServlet extends BaseServlet {
	
	private BookService service = new BookServiceImpl();
	Gson gson = new Gson();

	@Override
	public Object execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String encodedBook = req.getParameter("book");
		Book book = gson.fromJson(URLDecoder.decode(encodedBook, "UTF-8"), Book.class);
		Map<String, String> ret = new HashMap<String, String>();
		if(book.getBuytime() == null || "".equals(book.getBuytime())) {
			book.setBuytime(SpbmsUtils.date());
		}
		String uid = ((User)req.getSession().getAttribute("user")).getUsername();
		book.setUid(uid);
		if(service.add(book)) {
			ret.put("msg", "添加成功");
			ret.put("statu", "1");
		} else {
			ret.put("msg", "添加失败");
			ret.put("statu", "0");
		}
		return ret;
	}
	
	public Object query(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String bid = req.getParameter("bid");
		String username = ((User)req.getSession().getAttribute("user")).getUsername();
		Book b = new Book();
		b.setBid(bid);
		b.setUid(username);
		Book book = service.query(b);
		return book;
	}

	public Object queryList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String type = req.getParameter("type");
		String orderBy = req.getParameter("orderBy");
		String username = ((User)req.getSession().getAttribute("user")).getUsername();
		Book book = new Book();
		book.setUid(username);
		book.setBookname(req.getParameter("bookname"));
		book.setAuthor(req.getParameter("author"));
		book.setBuytime(req.getParameter("buytime"));
		List<Book> ret = service.queryList(book, type, orderBy);
		return ret;
	}
	
	public Object update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String bid = req.getParameter("bid");
		String encodedbook = req.getParameter("book");
		System.out.println(encodedbook);
		Book book = gson.fromJson(URLDecoder.decode(encodedbook, "UTF-8"), Book.class);
		User user = (User) req.getSession().getAttribute("user");
		book.setUid(user.getUsername());
		Map<String, String> ret = new HashMap<String, String>();
		if(service.update(book, bid) != null) {
			ret.put("statu", "1");
			ret.put("msg", "修改成功");
		} else {
			ret.put("statu", "0");
			ret.put("msg", "修改失败");
		}
		return ret;
	}
	
	public Object delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String bid = req.getParameter("bid");
		String uid = ((User)req.getSession().getAttribute("user")).getUsername();
		Book book = new Book(bid,null,null,null,null,uid);
		Map<String, String> ret = new HashMap<String, String>();
		if (service.delete(book)) {
			ret.put("statu", "1");
			ret.put("msg", "删除成功");
		} else {
			ret.put("statu", "0");
			ret.put("msg", "删除失败");
		}
		return ret;
	}
}
