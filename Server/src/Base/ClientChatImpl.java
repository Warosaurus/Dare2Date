package Base;

import Client.BasicChatReciever;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientChatImpl extends UnicastRemoteObject implements ClientChatInterface {
	
	public ClientChatImpl() throws RemoteException {
	}
	
	@Override
	public void recieveMail(Mail mail) {
		BasicChatReciever.msgRecieved(mail);
	}
}
