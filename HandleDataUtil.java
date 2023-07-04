import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HandleDataUtil {

	/* This class contains methods for serializing and deserializing
	 * data for users, books and issue books objects.
	 * */

	// fetch data from files using deserialization
	static <E> Object fetchDataFromFiles(String filePath) throws IOException, ClassNotFoundException {
		try {
			FileInputStream file = new FileInputStream(filePath);
			ObjectInputStream ip = new ObjectInputStream(file);
			file.close();
			ip.close();
			return ip.readObject();
		}
		catch(Exception e) {
			return new ArrayList<E>();
		}
	}

	// send data to files using serialization
	static void updateDataToFiles(Object data, String path) throws IOException {
		FileOutputStream file = new FileOutputStream(path);
		ObjectOutputStream op = new ObjectOutputStream(file);
		file.close();
		op.close();
		op.writeObject(data);
	}
}
