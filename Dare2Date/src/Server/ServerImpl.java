package Server;

import Base.*;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServiceInterface {

	private final ArrayList<User> userArray;
	private final ArrayList<UserServerInfo> userServerArray;
	private final ArrayList<Session> sessions;
	
	public ServerImpl() throws RemoteException {
		userServerArray = new ArrayList();
		sessions = new ArrayList();
		userArray = new ArrayList();
	}

	@Override
	public Response SignUp(UserServerInfo user) throws RemoteException {
		Response res = new Response();
		if (!validEmail(user)) {
			res.setError("This user already exists.");
		}
		else {
			user.setUserid(maxUserid());
			addUser(user);
			res.setResponse(true);
		}
		System.out.println("userArray: " + userServerArray);
		return res;
	}

	@Override
	public Response Login(String email, String pass) throws RemoteException {
		Response res = new Response();
		return res;
	}
	
	//add user
	public void addUser(UserServerInfo user) {
		synchronized (userServerArray) {
			userServerArray.add(user);
		}
		User newUser = basicUser(user);
		synchronized (userArray) {
			userArray.add(newUser);	
		}
	}
	
	public User basicUser(UserServerInfo server) {
		User basic = new User();
		basic.setFName(server.getFName());
		basic.setLName(server.getLName());
		basic.setAge(server.getAge());
		basic.setLevel(server.getLevel());
		basic.setUserid(server.getUserid());
		return basic;
	}
	
	//get the max userid to add to a new user.
	public int maxUserid () {
		int max = 1;//if there are no users the max id is 1, reuturn 1.
		if (!userServerArray.isEmpty()) {
			synchronized (userServerArray) {
				for (int i = 0; i < userServerArray.size(); i++){
					max = userServerArray.get(i).getUserid();
				}
			}
		}
		return max;
	}
	
	//check to see whether an email address has already been used to register.
	public boolean validEmail (UserServerInfo user) {
		boolean valid = true;
		//if there are no users then there is no point in checking if a specific email address is in use.
		if (!userServerArray.isEmpty()){
			int i = 0;
			//synchorinization adds locks  to data such that only one thread has access at a time.
			synchronized (userServerArray) {
				do {
					if(userServerArray.get(i).getEmail().equals(user.getEmail())){
						valid = false;
					}
					i++;
				}while(valid && i < userServerArray.size());
			}
		}
		return valid;
	}
	
	public User checkLoginDetails(String email, String pass) {
		User user = new User();
		boolean valid = false;
		//if there are no users then there is no point in checking if a specific email address is in use.
		if (!userServerArray.isEmpty()){
			int i = 0;
			//synchorinization adds locks  to data such that only one thread has access at a time.
			synchronized (userServerArray) {
				do {
					if((userServerArray.get(i).getEmail().equals(email)) && (userServerArray.get(i).getPass().equals(pass))){
						valid = true;
						user = userServerArray.get(i);
					}
					i++;
				}while(!valid && i < userServerArray.size());
			}
			
		}
		return user;
	}
	
	public User getUser (int userid) {
		User user = new User();
		
		return user;
	}
	
	public void createSession(User user) {
		Session session = new Session();
		session.setUserid(user.getUserid());
	}
	
	//a simple method to check whether a user exists
	public boolean exists (UserServerInfo user) {
		//synchorinization adds locks  to data such that only one thread has access at a time.
		synchronized (userServerArray) {
			//check to see whether a user exists within the array
			return userServerArray.contains(user);
		}
	}
}
