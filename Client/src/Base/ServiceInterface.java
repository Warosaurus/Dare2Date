package Base;

import java.util.Map;
import java.rmi.Remote;
import java.util.ArrayList;
//import java.net.InetAddress;
import java.rmi.RemoteException;


public interface ServiceInterface extends Remote {

	//Each method should indicate it throws RemoteException
	public Response SignUp(SignUp signUp) throws RemoteException;
	public Response Login(String email, String pass) throws RemoteException;
	public Response Logoff(int userid) throws RemoteException;
	public Response nameSearch(String keyword) throws RemoteException;
	public Response blindAgeMatch(User user) throws RemoteException;
	public Response blindLocationMatch(User user) throws RemoteException;
	public Response search(Map<String, ArrayList> map) throws RemoteException;
	public Response getOnlineUsers(User user) throws RemoteException;
	public Response setClientRmi(String ip,User user) throws RemoteException;
	public void sendMail(Mail mail) throws RemoteException;
}
