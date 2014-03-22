import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface extends Remote {
	//Each method should indicate it throws RemoteException
	public OnResponse SignUp(SignUp user) throws RemoteException;
	public OnResponse Login(String email, String pass) throws RemoteException;
	public OnResponse Logoff(int userid) throws RemoteException;
}