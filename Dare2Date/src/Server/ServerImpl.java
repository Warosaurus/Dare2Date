package Server;

import Base.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl extends UnicastRemoteObject implements ServiceInterface {

	private final ConcurrentHashMap<Integer,User> userMap;
	private final ConcurrentHashMap<Integer, Boolean> sessions;
	private final ConcurrentHashMap<Integer,UserServerInfo> userServerMap;
	
	public ServerImpl() throws RemoteException {
		userMap = new ConcurrentHashMap();
		sessions = new ConcurrentHashMap();
		userServerMap = new ConcurrentHashMap();
	}

	@Override
	public Response SignUp(UserServerInfo userserverinfo) throws RemoteException {
		Response res = new Response();
		if (!existingEmail(userserverinfo)) {
			res.setError("This email address has already been registerd.");
		}
		else {
			int userid = maxUserid();
			userserverinfo.setUserid(userid);
			userServerMap.put(userid,userserverinfo);
			User newUser = basicUser(userserverinfo);
			userMap.put(userid,newUser);
			res.setResponse(true);
		}
		System.out.println("userArray: " + userServerMap);
		return res;
	}

	@Override
	public Response Login(String email, String pass) throws RemoteException {
		Response res = new Response();
		UserServerInfo userserverinfo = checkLoginDetails(email, pass);
		if (userserverinfo != null) {
			User user = basicUser(userserverinfo);
			startSession(user.getUserid());
			res.setResponse(user);
		}
		else 
			res.setError("email and password combination does not match.");
		return res;
	}

	@Override
	public Response Logoff (int userid) {
		Response res = new Response();
		endSession(userid);
		res.setResponse(true);
		return res;
	}
	
	//maybe - rename
	public User basicUser(UserServerInfo server) {
		User basic = new User();
		basic.setFName(server.getFName());
		basic.setLName(server.getLName());
		basic.setAge(server.getAge());
		basic.setLevel(server.getLevel());
		basic.setUserid(server.getUserid());
		return basic;
	}

	//check to see whether an email address has already been registerd.
	public boolean existingEmail (UserServerInfo userserverinfo) {
		boolean valid = true;
		//if there are no users then there is no point in checking if a specific email address is in use.
		if (!userServerMap.isEmpty()){
			Iterator<Integer> iter = userServerMap.keySet().iterator();
			do {
				Integer i = iter.next();
				if(userServerMap.get(i).getEmail().equals(userserverinfo.getEmail())){
					valid = false;
				}
			}while(valid && iter.hasNext());
		}
		return valid;
	}
	
	//maybe - refactor to use existingEmail
	public UserServerInfo checkLoginDetails(String email, String pass) {
		UserServerInfo userserverinfo = new UserServerInfo();
		boolean valid = false;
		//if there are no users then there is no point in checking if a specific email address is registerd.
		if (!userServerMap.isEmpty()){
			Iterator<Integer> iter = userServerMap.keySet().iterator();
			do {
				Integer i = iter.next();
				if((userServerMap.get(i).getEmail().equals(email)) && (userServerMap.get(i).getPass().equals(pass))){
					valid = true;
					userserverinfo = userServerMap.get(i);
				}
			}while(!valid && iter.hasNext());
		}
		return userserverinfo;
	}

	//return the maximum userid to assign to a new user
	public int maxUserid() {
		//if userServerMap is not empty ? (return maximum keyset(userids) + 1) : (else return 1)
		return ((!userServerMap.isEmpty()) ? (Collections.max(userServerMap.keySet()) + 1) : 1);
	}
	
	//Used to return a specific user
	public User getUser (int userid) {
		return ((userMap.containsKey(userid)) ? userMap.get(userid) : null);
	}

	//start a users session	
	public void startSession(int userid) {
		sessions.put(userid,true);
	}
	
	//return whether the session of the user is active, if session doesn't exist returns false. Could be missleading but, eh..
	public boolean findSession(int userid) {
		return ((sessions.containsKey(userid)) ? sessions.get(userid) : false );
	}
	
	//end a users session
	public void endSession(int userid) {
		sessions.put(userid, false);
	}
}
