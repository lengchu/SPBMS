package cn.lenchu.domain;

public class Book {
	
	private String bid;
	private String bookname;
	private String author;
	private Double price;
	private String buytime;
	private String uid;
	
	public Book() {
	}

	public Book(String bid, String bookname, String author, Double price, String buytime, String uid) {
		this.bid = bid;
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.buytime = buytime;
		this.uid = uid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bookname=" + bookname + ", author=" + author + ", price=" + price + ", buytime="
				+ buytime + ", uid=" + uid + "]";
	}
}
