package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

<<<<<<< HEAD
/**
 *
 * @author gareth
 */
public interface ClientInterface extends Remote{
    
    public void receiveMail(Mail mail) throws RemoteException;
    public void updateUsers(ArrayList<User> users) throws RemoteException;
    
=======
public interface ClientInterface extends Remote {
	public void recieveMail(Mail mail) throws RemoteException;
>>>>>>> a2ae6f79b18bfd1765373666588f1301a017aa41
}
