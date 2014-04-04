package Client;

import Base.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class BasicChatSender {

	public static void main(String[] args) {
		try {
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			//Create the sender user on the server
			SignUp user = new SignUp();
			user.setE_Mail("example1@example.com");
			user.setFirstName("Mike");
			user.setSurName("hen");
			user.setGender("Male");
			user.setLevel(2);
			user.setLocation("Amsterdam");
			user.setAccountNumber("1234567891234567");
			user.setAge(20);
			user.setPassword("123456");
			service.SignUp(user);
			
			//Get user objects in a sly way..
			Response res1 = service.Login("example2@example.com", "12345678");
			User user1 = (User)res1.getResponse();
			Response res2 = service.Login("example1@example.com", "123456");
			User user2 = (User)res2.getResponse();
			
			//Creat the mail
			Mail mail = new Mail();
			mail.setSender(user1);
			mail.setReciever(user2);
			mail.setContent("Hello world!");
			//Send the mail.
			service.sendMail(mail);
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			System.out.println(e);
		}
	}
}
