import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
	
	// This is the main java file for the LMS. Execution happens from here.
	// There are two types of users- admins and users. Admins can search, add, 
	// delete, issue books, add other admins/users but users can only search a book.
	
	// Display a menu showing a list of options the user(admin/user) can make.
	// There are different menus for admins and normal users and display them accordingly.
	
	/* IMP - At the start of application, get user, book and book issue details using
	 *  deserialization and store them in respective arraylists. login as user or admin
	 * and use application
	 * */
	
	// display greetings when the application is started
	private static void greetings() {
		System.out.println("Welcome to Easy Manage LMS. Your gateway to knowledge and wisdom!!!");
		System.out.println("Please login to begin or continue with your learning journey.");
		System.out.println("\n");
	}
	
	// create book as per user details and return it to the caller
	private static Book createBook(Scanner sc) {
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
	
	// Execution starts from here
	public static void main(String[] args) {
		greetings(); // this is done
//		displayMenu(); // menu will be different for admin and users
		Scanner sc = new Scanner(System.in);
		ArrayList<Book> books = new ArrayList<Book>(); // this list is used to store book objects whose details are taken from cli
		int chosenOption = sc.nextInt(); 
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
//			displayMenu(); // to be coded later for admin and normal users
			chosenOption = sc.nextInt();
		}
		
		
		// admin or user has logged out of the application.
		System.out.println("Thank you, come again!");
		
		System.out.println("------------------");
		System.out.println(" HAPPY LEARNING !!");
		System.out.println("------------------");
		
		// serialize books, user and issue books arraylists so that they can be deserialized 
		// and used in later runs.
	}
	
	
	// This method deletes a book from the books arraylist
	private static void deleteBook(ArrayList<Book> books, int index) {
		Book book = books.remove(index);
		System.out.println("Book- " + book.getBookName() + "deleted successfully");
	}
	
	// This method displays all the details of a book
	private static void printBookDetails(Book book) {
		System.out.println("Book name- " + book.getBookName());
		System.out.println("Author name- " + book.getWriterName());
		System.out.println("Price- " + book.getBookPrice());
		System.out.println("Quantity- " + book.getBookQuantity());
		System.out.println();
	}

	// This method inputs string from the user and using that string, checks if the
	// book is available in the library or not
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
	
	// display list of actions an admin user can perform in this app
	private static void displayAdminMenu() {
		System.out.println("\n///////////////////////////////");
		System.out.println("Press 1 to add a book");
		System.out.println("Press 2 to search a book");
		System.out.println("Press 3 to delete a book");
		System.out.println("Press 4 to issue a book");
		System.out.println("Press 5 to display all books");
		System.out.println("Press 6 to add a user");
		System.out.println("Press 7 to exit");
		System.out.println("///////////////////////////////\n");
//		System.out.println();
	}

}
