package Server;

import Base.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

public class DateServer {
	public static void main(String[] args) {
		System.out.println("Server initialzing..");
		try {
			LocateRegistry.createRegistry(1099);
			ServiceInterface service = new ServerImpl();
			Naming.rebind("DateServer", service);
			System.out.println("Server Ready.");
		} catch (RemoteException ex) {
			System.out.println(ex);
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		}
	}
}
