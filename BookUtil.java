import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookUtil {
	/* This class contains utility methods such as
	 * adding, searching, deleting, displaying books
	 * as well as adding users
	 * */

	// create book as per user details and return it to the caller
	static Book createBook(Scanner sc) {
		System.out.println("Enter book details ----->\n");

		// this unique id is generated everytime a new book object is to be created
		String bookId = UUID.randomUUID().toString();

		System.out.println("Enter book name- ");
		String bookName = sc.nextLine();

		System.out.println("Enter author name- ");
		String writerName = sc.nextLine();

		System.out.println("Enter book price- ");
		double bookPrice = sc.nextDouble();

		System.out.println("Enter book quantity- ");
		int bookQuantity = sc.nextInt();

		Book book = new Book(bookId, bookName, writerName, bookPrice, bookQuantity);
		return book;
	}

	// issue book to the users
	static IssueBook issueBook(Scanner sc, ArrayList<Book> books, ArrayList<User> users) {
		System.out.println("Enter name of the book to issue");
		String bookName = sc.nextLine();
		
		Book book = findBook(bookName, books);
		if(book == null || book.getBookQuantity() < 1) return null;
		
		System.out.println("Enter username of the person to whom to issue book- ");
		String username = sc.nextLine();
		
		String userId = findUser(username, users);
		
		return new IssueBook(book.getBookId(), userId);
	}

	// helper method to find book from books array using book name
	private static Book findBook(String bookName, ArrayList<Book> books) {
		for(Book book: books) {
			if(book.getBookName().equalsIgnoreCase(bookName)) return book;
		}
		return null;
	}

	// helper method to find user from users array using user name
	private static String findUser(String username, ArrayList<User> users) {
		for(User user: users) {
			// check case sensitive as not logging in
			if(user.getUsername().equalsIgnoreCase(username)) return user.getUsername();
		}
		return null;
	}

	// This method deletes a book from the books arraylist
	static void deleteBook(ArrayList<Book> books, int index) {
		Book book = books.remove(index);
		System.out.println("Book- " + book.getBookName() + "deleted successfully");
	}

	// This method displays all the details of a book
	static void printBookDetails(Book book) {
		System.out.println("Book name- " + book.getBookName());
		System.out.println("Author name- " + book.getWriterName());
		System.out.println("Price- " + book.getBookPrice());
		System.out.println("Quantity- " + book.getBookQuantity());
		System.out.println();
	}

	// find book using book name or write name from books array. if not found, print not found
	static int findBook(Scanner sc, ArrayList<Book> books) {
		if(books.size() == 0) {
			System.out.println("No books available in the library. Check back later.");
			return -1;
		}
		System.out.println("Enter name of the book or author's name- ");
		String searchWord = sc.nextLine();
		searchWord = searchWord.trim().toLowerCase();
		for(int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			String name = book.getBookName().toLowerCase();
			String author = book.getWriterName().toLowerCase();
			if(name.contains(searchWord) || author.contains(searchWord)) {
				return i;
			}
		}
		System.out.println("No book found with the given name. Try to enter full name of the book");
		return -1;
	}
	
	static void displayAllBooks(ArrayList<Book> books) {
		for(Book book: books) {
			System.out.println(book.toString());
		}
	}
}
