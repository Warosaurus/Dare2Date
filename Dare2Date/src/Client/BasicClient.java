package Client;

import Base.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class BasicClient {

	public void signUp() {
		try {
			//Test User
			User user = new User();
			user.setFName("John");
			user.setLName("Mc");
			user.setAge(33);
			user.setPass("Swag69");
			//Create a reference to the service interface at the location.
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			//Create a response object
			Response res = new Response();
			//Invoke create SignUp method
			res = service.SignUp(user);
			//Test response
			if (res.getError().equals(""))
				System.out.println("There was an error.");
			else 
				System.out.println("Everything went okay.");
		} catch (NotBoundException ex) {
			System.out.println(ex);
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		} catch (RemoteException ex) {
			System.out.println(ex);
		}
	}
	
	public static void main(String[] args){
		BasicClient bc = new BasicClient();
		bc.signUp();
	}
}
