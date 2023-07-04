
public class DisplayMenuUtil {
	/* This class is used for the sole purpose
	 * of displaying menu to the user based on
	 * the user role- admin/user
	 * */

	static void displayMenu(boolean isAdmin, String username) {
		if(isAdmin) {
			displayAdminMenu(username);
		}
		else {
			displayUserMenu(username);
		}
	}

	// display a list off actions for a normal user
	private static void displayUserMenu(String username) {
		System.out.println("Welcome, " + username + "! (User)");
		System.out.println("What would you like to do today?");
		System.out.println("\n///////////////////////////////");
		System.out.println("   Press 2 to search a book");
		System.out.println("   Press 5 to display all books");
		System.out.println("   Press 7 to exit");
		System.out.println("///////////////////////////////\n");
	}

	// display list of actions an admin user can perform in this app
	private static void displayAdminMenu(String username) {
		System.out.println("Welcome, " + username + "! (Admin)");
		System.out.println("What would you like to do today?");
		System.out.println("\n///////////////////////////////");
		System.out.println("   Press 1 to add a book");
		System.out.println("   Press 2 to search a book");
		System.out.println("   Press 3 to delete a book");
		System.out.println("   Press 4 to issue a book");
		System.out.println("   Press 5 to display all books");
		System.out.println("   Press 6 to add a user");
		System.out.println("   Press 7 to exit");
		System.out.println("///////////////////////////////\n");
	}
}
