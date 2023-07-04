import java.io.Serializable;

public class IssueBook implements Serializable {
	
	private static final long serialversionUID =
            129343487L; // used to identify if sender and receiver have compatible classes for working with 
						// serialized and deserialized objects
	
	private String bookId;
	private String userId;
	
	public IssueBook(String bookId, String userId) {
		this.bookId = bookId;
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
