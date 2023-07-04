import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {

	// This is the main java file for the LMS. Execution happens from here.
	// There are two types of users- admins and users. Admins can search, add, 
	// delete, issue books, add other admins/users but users can only search a book.
	// Also both admins and users can list all books available

	/* !!! NOTE !!! - change usersFilePath, booksFilePath, issuedBooksFilePath to where you
	/* want to create .txt files for storing the data
	 * */
	
	// still left - need to add functionality to delete user, ability to login after until user exits the app, 
	// some more maybe. and maybe some bugs exist. need to test program

	// display greetings when the application is started
	static void greetings() {
		System.out.println("Welcome to Easy Manage LMS. Your gateway to knowledge and wisdom!!!\n");
	}

	// Execution starts from here
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		greetings();
		Scanner sc = new Scanner(System.in);
		
		// users details filepath
		String usersFilePath = "C:\\Users\\Asus\\apna college\\EasyManageLMS\\userDetails.txt";
		
		// retrieve users details using deserialization
		ArrayList<User> users = (ArrayList<User>) HandleDataUtil.fetchDataFromFiles(usersFilePath);

		// if no users present, create a new user
		if(users.isEmpty()) {
			System.out.println("Sign up to begin with your learning journey");
			User mainAdmin = UserUtil.createNewUser(sc, true);
			users.add(mainAdmin);
		}
		// file paths for books and issued books .txt files
		String booksFilePath = "C:\\Users\\Asus\\apna college\\EasyManageLMS\\bookDetails.txt";
		String issuedBooksFilePath = "C:\\Users\\Asus\\apna college\\EasyManageLMS\\issueBook.txt";
		
		// retrieve books and issued books details using deserialization
		ArrayList<Book> books = (ArrayList<Book>) HandleDataUtil.fetchDataFromFiles(booksFilePath);
		ArrayList<IssueBook> issuedBooks = (ArrayList<IssueBook>) HandleDataUtil.fetchDataFromFiles(issuedBooksFilePath);

		// ask to login
		User loggedInUser = UserUtil.login(sc, users);
		// user logged in session
		loggedInSession(loggedInUser, sc, books, issuedBooks, users);
		sc.close();
		
		// admin or user has logged out of the application.
		System.out.println("Thank you, " + loggedInUser.getUsername() + " come again!");
		System.out.println("------------------");
		System.out.println(" HAPPY LEARNING !!");
		System.out.println("------------------");

		// serialize books, user and issue books arraylists so that they can be deserialized 
		// and used in later runs.
		HandleDataUtil.updateDataToFiles(users, usersFilePath);
		HandleDataUtil.updateDataToFiles(books, booksFilePath);
		HandleDataUtil.updateDataToFiles(issuedBooks, issuedBooksFilePath);
	}

	private static void loggedInSession(User loggedInUser, Scanner sc, ArrayList<Book> books, ArrayList<IssueBook> issuedBooks, ArrayList<User> users) {

		boolean isAdmin = loggedInUser.getRole().equals("Admin");
		DisplayMenuUtil.displayMenu(isAdmin, loggedInUser.getUsername());
		int chosenOption = sc.nextInt();
		
		while(true) {
			if(chosenOption < 1 || chosenOption > 7) {
				System.out.println("Please enter the correct number!");
			}
			else if(chosenOption == 1 && isAdmin) {
				Book book = BookUtil.createBook(sc);
				books.add(book);
				System.out.println("Book added to library");
			}
			// search book from books arraylist for both users and admins
			else if(chosenOption == 2) {
				int index = BookUtil.findBook(sc, books);
				if(index != -1) BookUtil.printBookDetails(books.get(index));
			}
			// delete a book only if admin
			else if(chosenOption == 3 && isAdmin) {
				int index = BookUtil.findBook(sc, books);
				if(index != -1) BookUtil.deleteBook(books, index);
			}
			else if(chosenOption == 4 && isAdmin) {
				IssueBook issue = BookUtil.issueBook(sc, books, users);
				if(issue == null) System.out.println("Either book is not found or book is not available for lending");
				else issuedBooks.add(issue);
			}
			else if(chosenOption == 5) {
				BookUtil.displayAllBooks(books);
			}
			else if(chosenOption == 6 && isAdmin) {
				System.out.println("Do you want to create a new admin account or a normal user account?");
				System.out.println("Enter \"true\" for admin and \"false\" for user");
				boolean wantAdmin = sc.nextBoolean();
				User newUser = UserUtil.createNewUser(sc, wantAdmin);
				users.add(newUser);
			}
			else if(chosenOption == 7) {
				System.out.println("logging out " + loggedInUser.getUsername());
				break; // when user logs out, break out of the loop which emulates login session
			}
			DisplayMenuUtil.displayMenu(isAdmin, loggedInUser.getUsername());
			chosenOption = sc.nextInt();
		}
	}
}
