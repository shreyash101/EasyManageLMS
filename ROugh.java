import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class ROugh {
	
	// this is a rough class used to test code.
	
	public static void main(String[] args) throws Exception {
//		UUID uid = 
//		ArrayList<Book> list = new ArrayList<>();
//		Book b1 = new Book(UUID.randomUUID().toString(), "some name", "some writer", 903.32,32);
//		Book b2 = new Book(UUID.randomUUID().toString(), "be kind", "andy", 673.32,54);
//		list.add(b1);
//		list.add(b2);
//		FileOutputStream file = new FileOutputStream("C:\\Users\\Asus\\apna college\\EasyManageLMS\\testfile.txt");
//		ObjectOutputStream op = new ObjectOutputStream(file);
////		System.out.println(UUID.randomUUID().getClass());
//		op.writeObject(list); (ArrayList<Book>)
//		file.close();
//		op.close();
		
		// create user array, add users to it.
		// send array
		// fetch array
//		ArrayList<User> users = new ArrayList<>();
//		User u1 = new User(UUID.randomUUID().toString(), "shrey", "afy823", "Admin");
//		users.add(u1);
//		
//		FileOutputStream file = new FileOutputStream("C:\\Users\\Asus\\apna college\\EasyManageLMS\\userDetails.txt");
//		ObjectOutputStream op = new ObjectOutputStream(file);
//		op.writeObject(users);
		
//		ArrayList<User> newUser = (ArrayList<User>) fetchDataFromFiles("C:\\Users\\Asus\\apna college\\EasyManageLMS\\userDetails.txt");
////		// print deserialized objects
//		System.out.println("new user size- " + newUser.isEmpty());
////		User u1 = new User(UUID.randomUUID().toString(), "shrey", "afy823", "Admin");
////		newUser.add(u1);
//		for(int i = 0; i < newUser.size(); i++) {
//			System.out.println(newUser.get(i).toString());
//		}
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter a new number- ");
//		int n = sc.nextInt();
//		System.out.println("YOur number is- " + n);
//		System.out.println("Enter next no");
		String a = "a";
		String b = "a";
		System.out.println(a.equals(b));
	}
	
	private static <E> Object fetchDataFromFiles(String filePath) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(filePath);
		try {
			ObjectInputStream ip = new ObjectInputStream(file);
			System.out.println("after error");
			return ip.readObject();
		}
		catch(Exception e) {
			System.out.println("the file must be empty");
		}
		return new ArrayList<E>();
	}
	
}
