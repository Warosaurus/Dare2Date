package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientChatInterface extends Remote {
	public void recieveMail(Mail mail) throws RemoteException;
}
