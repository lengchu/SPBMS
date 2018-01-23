package cn.lenchu.test;

import java.util.List;

import org.junit.Test;
import com.google.gson.Gson;
import cn.lenchu.book.dao.BookDao;
import cn.lenchu.book.dao.impl.BookDaoImpl;
import cn.lenchu.domain.Book;
import cn.lenchu.utils.SpbmsUtils;

public class BookDaoTest {
	
	static BookDao dao = new BookDaoImpl();
	static Gson gson = new Gson();
	
	@Test
	public void testAdd() {
		Book book = new Book("3", "红楼梦", "曹雪芹", 20.00, SpbmsUtils.date(), "admin");
		System.out.println(dao.add(book));
	}
	
	@Test
	public void testUpdate() {
		Book book = new Book("1", "纳兰词", "苏缨", 19.98, SpbmsUtils.date(), "admin");
		System.out.println(dao.update(book, "1"));
	}
	
	@Test
	public void testQueryOne() {
		Book b = new Book();
		b.setUid("admin");
		b.setBid("1");
		Book book = dao.query(b);
		System.out.println(gson.toJson(book));
	}
	
	@Test
	public void testQueryList() {
		Book book = new Book();
		book.setAuthor("曹雪芹");
		List<Book> list = dao.queryList(book, "author", "bookname");
		System.out.println(gson.toJson(list));
	}

}
