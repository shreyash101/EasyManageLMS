import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
	
	// display greetings to the user
	private static void greetings() {
		System.out.println("Welcome to Easy Manage LMS.");
		System.out.println("What would you like to do today- ");
	}
	
	// create book as per user details and return it to the caller
	private static Book createBook(Scanner sc) {
		System.out.println("Enter book details ----->\n");
		// this id is generated everytime new book object is to be created
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

	public static void main(String[] args) { // assign nos to each action, like storing book, retrieving book, delete book, etc.
		greetings();
		displayMenu();
		Scanner sc = new Scanner(System.in);
		ArrayList<Book> books = new ArrayList<Book>(); // this list is used to store book objects whose details are taken from cli
		int chosenOption = sc.nextInt(); // press 0 for exit, 1 => add book
		while(chosenOption != 0) {
			switch(chosenOption) {
			// create book object and add it to books array
			case 1: {
				Book book = createBook(sc);
				books.add(book);
				System.out.println("Book added to library");
				break;
			}
			case 2: {
				// code for searching book from books arraylist to be added here
				int index = findBook(sc, books);
				if(index != -1) printBookDetails(books.get(index));
				break;
			}
			case 3: {
				// code for deleting book from books arraylist to be added here
				int index = findBook(sc, books);
				if(index != -1) deleteBook(books, index);
				break;
			}
			}
			displayMenu();
			chosenOption = sc.nextInt();
		}
		System.out.println("Thank you, come again!");
	}
	
	

	private static void deleteBook(ArrayList<Book> books, int index) {
		Book book = books.remove(index);
		System.out.println("Book- " + book.getBookName() + "deleted successfully");
	}

	private static void printBookDetails(Book book) {
		System.out.println("Book name- " + book.getBookName());
		System.out.println("Author name- " + book.getWriterName());
		System.out.println("Price- " + book.getBookPrice());
		System.out.println("Quantity- " + book.getBookQuantity());
		System.out.println();
	}

	private static int findBook(Scanner sc, ArrayList<Book> books) {
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
		System.out.println("No book found with the given name.");
		return -1;
	}
	
	// display list of actions a user can perform in this app
	private static void displayMenu() {
		System.out.println("\n///////////////////////////////");
		System.out.println("Press 1 for adding books");
		System.out.println("Press 2 to search a book");
		System.out.println("Press 3 to delete a book");
		System.out.println("Press 4 to display all books");
		System.out.println("Press 0 to exit");
		System.out.println("///////////////////////////////\n");
//		System.out.println();
		
	}

}
