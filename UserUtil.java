import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UserUtil {

	/*  This class contains utiltiy methods 
	 * related to user such as login, create new user
	 * */

	// create new user using username and password and assign role- whether admin/user
	static User createNewUser(Scanner sc, boolean wantAdmin) {
		if(wantAdmin) {
			System.out.println("Create a new admin account");
		}
		else {
			System.out.println("Create a new user account");
		}
		System.out.println("Enter a username- ");
		String username = sc.nextLine();
		System.out.println("Enter a password- ");
		String password = sc.nextLine();
		return new User(UUID.randomUUID().toString(), username, password, wantAdmin ? "Admin" : "User");
	}

	// login form
	static User login(Scanner sc, ArrayList<User> users) {
		System.out.println("===============");
		System.out.println("     LOGIN     ");
		System.out.println("===============");
		System.out.println("Enter username- ");
		String username = sc.nextLine();
		System.out.println("Enter password- ");
		String password = sc.nextLine();

		// iterate thru users array to check if user exists
		boolean userFound = false;
		while(!userFound) {
			for(int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
					userFound = true;
					return user;
				}
			}
			System.out.println("Either username or password is incorrect. Please enter correct details");
		}
		return null;
	}
}
