package cn.lenchu.book.dao;

import java.util.List;

import cn.lenchu.domain.Book;

public interface BookDao {
	
	Book query(Book book);
	
	/**
	 * 查询多本书
	 * @param book 欲查询书的特征
	 * @param type 通过哪个字段查(bookname, author)为其他时时查询所有
	 * @param orderBy 通过哪个字段排序(bookname, author, buytime)为空时不排序
	 * @return
	 */
	List<Book> queryList(Book book, String type, String orderBy);
	
	boolean add(Book book);
	
	int addList(List<Book> bookList);
	
	Book update(Book book, String bid);
	
	boolean delete(Book book);
	
	int deleteList(List<Book> bookList);
}
