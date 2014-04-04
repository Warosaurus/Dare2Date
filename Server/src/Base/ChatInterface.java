package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
	public void login(User user) throws RemoteException;
	public void logout(User user) throws RemoteException;
	public void sendMail(Mail mail) throws RemoteException;
	public Mail recieveMail() throws RemoteException;
}
