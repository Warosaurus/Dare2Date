package Server;

import Base.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

/**
 *
 *
 */
public class DateServer {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Server initialzing..");
		try {
			//Create a local registry at port 1099.
			LocateRegistry.createRegistry(1099);
			//Create local instance of Service Interface called service.
			ServiceInterface service = new ServerImpl(); 
			//Set the alias of the registry to "DateServer".
			Naming.rebind("DateServer", service);
			System.out.println("Server Ready.");
		} catch (RemoteException ex) {
			System.out.println(ex);
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		}
	}
}
