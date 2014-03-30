package Client;

import Base.*;
import java.rmi.Naming;
import java.util.Scanner.*; //Temp for testing
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class BasicClient {

	public static void testUsers() {
		BasicClient bc = new BasicClient();
		SignUp[] test = new SignUp[5];

		SignUp user1 = new SignUp();
		user1.setE_Mail("example1@example.com");
		user1.setFirstName("Mike");
		user1.setSurName("hen");
		user1.setGender("Male");
		user1.setLevel(2);
		user1.setLocation("Amsterdam");
		user1.setAccountNumber("32155642135");
		user1.setAge(20);
		user1.setPassword("123456");
		test[0] = user1;

		SignUp user2 = new SignUp();
		user2.setE_Mail("example2@example.com");
		user2.setFirstName("John");
		user2.setSurName("Paul");
		user2.setGender("Male");
		user2.setLevel(3);
		user2.setLocation("Haarlem");
		user2.setAccountNumber("32155642135");
		user2.setAge(31);
		user2.setPassword("12345678");
		test[1] = user2;

		for (int i = 0; i < 2; i++) {
			bc.signUp(test[i]);
		}
	}

	public static void testNameSearch() {
		BasicClient bc = new BasicClient();
		bc.nameSearch("Mike");
	}

	public void nameSearch(String keyword) {
		try {
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			Response res = new Response();
			res = service.nameSearch(keyword);
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
		testUsers();
		testNameSearch();
	}
}
