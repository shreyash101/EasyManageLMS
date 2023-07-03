
public class Book {
	
	public Book(String bookId, String bookName, String writerName, double bookPrice, int bookQuantity) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.writerName = writerName;
		this.bookPrice = bookPrice;
		this.bookQuantity = bookQuantity;
	}
	
	private String bookId;
	private String bookName;
	private String writerName;
	private double bookPrice;
	private int bookQuantity;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public int getBookQuantity() {
		return bookQuantity;
	}
	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	
}
