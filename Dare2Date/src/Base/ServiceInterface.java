package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface extends Remote {
	//Each method should indicate it throws RemoteException
	public Response SignUp(UserServerInfo user) throws RemoteException;
	public Response Login(String email, String pass) throws RemoteException;
}
