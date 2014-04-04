/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author gareth
 */
public interface ClientInterface extends Remote{
    
    public void receiveMail(Mail mail) throws RemoteException;
    public void updateUsers(ArrayList<User> users) throws RemoteException;
    
}
