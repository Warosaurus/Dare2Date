package Client;

import Base.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BasicChatReciever {

	public static void main(String[] args) {
		try {
			
			System.out.println("Connecting to server");
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			
			//Create the reciever user on the server
			SignUp user2 = new SignUp();
			user2.setE_Mail("example2@example.com");
			user2.setFirstName("John");
			user2.setSurName("Paul");
			user2.setGender("Male");
			user2.setLevel(3);
			user2.setLocation("Haarlem");
			user2.setAccountNumber("1234567891234567");
			user2.setAge(31);
			user2.setPassword("12345678");
			service.SignUp(user2);
			
			Response res = service.Login("example2@example.com", "12345678");
			User user = (User)res.getResponse();
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			LocateRegistry.createRegistry(1098);
			ClientChatInterface chat = new ClientChatImpl();
			service.chatSignIn(user.getUserid(), ip);
			Naming.rebind("DateClient", chat);
		}
		catch (NotBoundException | MalformedURLException | RemoteException | UnknownHostException e) {
			System.out.println(e);
		}
	}
	
	public static void msgRecieved(Mail mail) {
		System.out.println("From:" + mail.getSender().getFName() + " Content: " + mail.getContent());
	}

}
