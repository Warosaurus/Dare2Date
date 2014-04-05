package Client;

import Base.*;
import java.util.Map;
import java.util.ArrayList;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class BasicClient {

	public static void createUsers() {
		BasicClient bc = new BasicClient();
		SignUp[] test = new SignUp[5];

		SignUp user1 = new SignUp();
		user1.setE_Mail("example1@example.com");
		user1.setFirstName("Mike");
		user1.setSurName("hen");
		user1.setGender("Male");
		user1.setLevel(2);
		user1.setLocation("Amsterdam");
		user1.setAccountNumber("1234567891234567");
		user1.setAge(20);
		user1.setPassword("123456");
		user1.setSexPref("Female");
		test[0] = user1;

		SignUp user2 = new SignUp();
		user2.setE_Mail("example2@example.com");
		user2.setFirstName("Jenny");
		user2.setSurName("Paula");
		user2.setGender("Female");
		user2.setLevel(3);
		user2.setLocation("Amsterdam");
		user2.setAccountNumber("1234567891234567");
		user2.setAge(20);
		user2.setPassword("12345678");
		user2.setSexPref("Male");
		test[1] = user2;

		for (int i = 0; i < 2; i++) {
			bc.signUp(test[i]);
		}
	}

	public static void test() {
		try {
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			Response res = new Response();
			res = service.Login("example1@example.com", "123456");
			User user = (User)res.getResponse();
			res = service.blindAgeMatch(user);
			if (res.getError() != null) {
				System.out.println(res.getResponse());
			}
		} catch (NotBoundException ex) {
			System.out.println(ex);
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		} catch (RemoteException ex) {
			System.out.println(ex);
		}
	}

	public void signUp(SignUp user) {
		try {
			//Create a reference to the service interface at the location.
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			//Create a response object
			Response res = new Response();
			//Invoke server SignUp method
			res = service.SignUp(user);
			//Test response
			if (res.getError() != null) {
				System.out.println(res.getError());
				System.out.println("There was an error.");
			} else {
				System.out.println("Everything went okay.");
				System.out.println(res.getResponse());
			}
		} catch (NotBoundException ex) {
			System.out.println(ex);
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		} catch (RemoteException ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		createUsers();
		test();
	}
}
