package cn.lenchu.book.service.impl;

import java.util.List;

import cn.lenchu.book.dao.BookDao;
import cn.lenchu.book.dao.impl.BookDaoImpl;
import cn.lenchu.book.service.BookService;
import cn.lenchu.domain.Book;

public class BookServiceImpl implements BookService {
	
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public Book query(Book book) {
		return bookDao.query(book);
	}

	@Override
	public List<Book> queryList(Book book, String type, String orderBy) {
		return bookDao.queryList(book, type, orderBy);
	}

	@Override
	public boolean add(Book book) {
		return bookDao.add(book);
	}

	@Override
	public int addList(List<Book> bookList) {
		return bookDao.addList(bookList);
	}

	@Override
	public Book update(Book book, String bid) {
		return bookDao.update(book, bid);
	}

	@Override
	public boolean delete(Book book) {
		return bookDao.delete(book);
	}

	@Override
	public int deleteList(List<Book> bookList) {
		return bookDao.deleteList(bookList);
	}

}
