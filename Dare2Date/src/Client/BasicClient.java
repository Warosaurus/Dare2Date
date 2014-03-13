package Client;

import Base.*;
import java.rmi.Naming;
import java.util.Scanner.*; //Temp for testing
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class BasicClient {
	
	public static void testUsers(){
		BasicClient bc = new BasicClient();
		UserServerInfo[] test = new UserServerInfo[5];
		
		UserServerInfo user1 = new UserServerInfo();
		user1.setEmail("example@example.com");
		user1.setFName("John");
		user1.setLName("Mc");
		user1.setAge(33);
		user1.setPass("123456");
		test[0] = user1;
		
		UserServerInfo user2 = new UserServerInfo();
		user2.setEmail("example@example.com");
		user2.setFName("Mike");
		user2.setLName("hen");
		user2.setAge(20);
		user2.setPass("123456");
		test[1] = user2;

		for (int i = 0; i < 2; i++){
			bc.signUp(test[i]);
		}
	}	
	
	public void signUp(UserServerInfo user) {
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
			}
			else {
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
	
	public static void main(String[] args){
		testUsers();
	}
}
