package Base;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

public class ServerChatImpl extends UnicastRemoteObject implements ServerChatInterface {

	private final ConcurrentHashMap<Integer, InetAddress> clientIps;

	public ServerChatImpl() throws RemoteException {
		clientIps = new ConcurrentHashMap();
	}

	@Override
	public void signIn(int userid, InetAddress ip) throws RemoteException {
		clientIps.put(userid, ip);
	}

	@Override
	public void signOut(int userid) throws RemoteException {
		clientIps.remove(userid);
	}

	@Override
	public void sendMail(Mail mail) throws RemoteException {
		//figure out where this message is going.
		
	}
}
