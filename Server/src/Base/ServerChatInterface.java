package Base;

import java.rmi.Remote;
import java.net.InetAddress;
import java.rmi.RemoteException;

public interface ServerChatInterface extends Remote {
	public void signIn(int userid, InetAddress ip) throws RemoteException;
	public void signOut(int userid) throws RemoteException;
	public void sendMail(Mail mail) throws RemoteException;
}
