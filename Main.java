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

	/* !!! NOTE - before you run !!! 
	 * 1. Change usersFilePath, booksFilePath, issuedBooksFilePath to where you
	 * want to create .txt files for storing the data. if files don't already
	 * exist, this program will create files.
	 * 
	 * 2. Suggest ideas to make this program more modular and in tune 
	 * with OOPS principles. I've just made a bunch of util classes to get my job done.
	 * Hope this program works with fewest bugs :)
	 * */
	
	/* !!! STILL LEFT !!! 
	 * 1. Need to add functionality to delete user.
	 * 2. Ability to login even after a user logs out until user exits the app.
	 * 3. When user submits book after issuing, add it to the books (increase it's quantity by 1).
	 * 4. Need to display info of whatever is added- user/book/issued book so that programmer 
	 * can store this info and test program
	 * */
	
	/* !!! NOTED BUGS !!!
	 * 1. Getting user password from user object using public getter to check if password entered matches user password.
	 * Need to mark getPassword() method as private and find another way to safely get user password
	 * to check during authentication
	 * */

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

	// user logged in session
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
