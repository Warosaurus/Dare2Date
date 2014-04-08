package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	public void receiveMail(Mail mail) throws RemoteException;
}
