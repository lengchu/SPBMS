package cn.lenchu.book.dao.impl;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lenchu.book.dao.BookDao;
import cn.lenchu.domain.Book;
import cn.lenchu.utils.DataSourceUtils;

public class BookDaoImpl implements BookDao {

	@Override
	public Book query(Book book) {
		String sql = "select * from book where bid=? and uid=?";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		Book b = null;
		try {
			b = run.query(sql, new BeanHandler<Book>(Book.class), book.getBid(), book.getUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	/**
	 * 查询多本书
	 * @param book 欲查询书的特征
	 * @param type 通过哪个字段查(bookname, author)为其他时查询所有
	 * @param orderBy 通过哪个字段排序(bookname, author, buytime)为空时不排序
	 * @return
	 */
	public List<Book> queryList(Book book, String type, String orderBy) {
		String sql = "select * from book";
		String typeValue = null;
		if ("bookname".equalsIgnoreCase(type)) {
			sql = sql + " where uid=? and bookname=?";
			typeValue = book.getBookname();
		} else if ("author".equalsIgnoreCase(type)) {
			sql = sql + " where uid=? and author=?";
			typeValue = book.getAuthor();
		} else {
			sql = sql + " where uid=?";
		}
		if ("bookname".equalsIgnoreCase(orderBy)) {
			sql = sql + " order by bookname";
		} else if ("author".equalsIgnoreCase(orderBy)) {
			sql = sql + " order by author";
		} else if ("buytime".equalsIgnoreCase(orderBy)) {
			sql = sql + " order by buytime";
		} else {
			sql = sql + " order by bid";
		}
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		List<Book> list = new LinkedList<Book>();
//		System.out.println(sql);
		try {
			if (typeValue == null) {
				list = run.query(sql, new BeanListHandler<Book>(Book.class), book.getUid());
			} else {
				list = run.query(sql, new BeanListHandler<Book>(Book.class), book.getUid(), typeValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(Book book) {
		String sql = "insert into book(bid,bookname,author,price,buytime,uid) values(?,?,?,?,?,?)";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		int result = 0;
		try {
			result = run.update(sql, book.getBid(), book.getBookname(), book.getAuthor(),
									 book.getPrice(), book.getBuytime(), book.getUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result != 0;
	}

	@Override
	public int addList(List<Book> bookList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Book update(Book book, String bid) {
		String sql = "update book set bid=?,bookname=?,author=?,price=?,buytime=? where uid=? and bid=?";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		Book result = null;
		try {
			run.update(sql, book.getBid(), book.getBookname(), book.getAuthor(), book.getPrice(), 
									 book.getBuytime(), book.getUid(), bid);
			result = book;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Book book) {
		String sql = "delete from book where bid=? and uid=?";
		QueryRunner run = new QueryRunner(DataSourceUtils.getDataSource());
		int result = 0;
		try {
			result = run.update(sql, book.getBid(), book.getUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result != 0;
	}

	@Override
	public int deleteList(List<Book> bookList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
