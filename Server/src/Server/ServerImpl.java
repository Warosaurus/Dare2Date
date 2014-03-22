package Server;

import Base.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 */
public class ServerImpl extends UnicastRemoteObject implements ServiceInterface {

	private final ConcurrentHashMap<Integer, User> userMap;
	private final ConcurrentHashMap<Integer, Boolean> sessions;
	private final ConcurrentHashMap<Integer, UserServerInfo> userServerMap;

	/**
	 *
	 * @throws java.rmi.RemoteException Default constructor.
	 */
	public ServerImpl() throws RemoteException {
		userMap = new ConcurrentHashMap();
		sessions = new ConcurrentHashMap();
		userServerMap = new ConcurrentHashMap();
	}

	/**
	 *
	 * @param signUp
	 * @return Response
	 * @throws RemoteException
	 */
	@Override
	public Response SignUp(SignUp signUp) throws RemoteException {
		Response res = new Response();
		if (!existingEmail(signUp.getE_Mail())) {
			res.setError("This email address has already been registerd.");
		} else {
			//return the max userid so that the next one can be assigned to a new user
			int userid = maxUserid() + 1;
			User user = new User(signUp.getFirstName(), signUp.getSurName(),signUp.getGender(), userid, signUp.getLevel(),signUp.getAge(), signUp.getBirthdate(), signUp.getLocation());
			//create a UserSerinfo object based on the SignUp object
			UserServerInfo userserverinfo = (UserServerInfo) user;
			//UserServerInfo userserverinfo = new UserServerInfo();
			userserverinfo.setEmail(signUp.getE_Mail());
			userserverinfo.setPass(signUp.getPassword());
			//set the id for the user
			userserverinfo.setUserid(userid);
			//place the userserverinfo object into the hashmap
			userServerMap.put(userid, userserverinfo);
			//place the user object into the the hashmap
			userMap.put(userid, user);
			res.setResponse(true);
		}
		System.out.println("userArray: " + userServerMap);
		return res;
	}

	/**
	 *
	 * @param email
	 * @param pass
	 * @return Response
	 * @throws RemoteException
	 */
	@Override
	public Response Login(String email, String pass) throws RemoteException {
		Response res = new Response();
		UserServerInfo userserverinfo = checkLoginDetails(email, pass);
		if (userserverinfo != null) {
			User user = userMap.get(userserverinfo.getUserid());
			startSession(user.getUserid());
			res.setResponse(user);
		} else {
			res.setError("email and password combination does not match.");
		}
		return res;
	}

	/**
	 *
	 * @param userid
	 * @return Response
	 */
	@Override
	public Response Logoff(int userid) {
		Response res = new Response();
		endSession(userid);
		res.setResponse(true);
		return res;
	}

	/**
	 * Check to see whether an email address has already been registered.
	 *
	 * @param email
	 * @return boolean
	 */
	public boolean existingEmail(String email) {
		boolean valid = true;
		//if there are no users then there is no point in checking if a specific email address is in use.
		if (!userServerMap.isEmpty()) {
			Iterator<Integer> iter = userServerMap.keySet().iterator();
			do {
				Integer i = iter.next();
				if (userServerMap.get(i).getEmail().equals(email)) {
					valid = false;
				}
			} while (valid && iter.hasNext());
		}
		return valid;
	}

	/**
	 *
	 * @param email
	 * @param pass
	 * @return UserServerInfo
	 */
	public UserServerInfo checkLoginDetails(String email, String pass) {
		UserServerInfo userserverinfo = new UserServerInfo();
		boolean valid = false;
		//if there are no users then there is no point in checking if a specific email address is registerd.
		if (!userServerMap.isEmpty()) {
			Iterator<Integer> iter = userServerMap.keySet().iterator();
			do {
				Integer i = iter.next();
				if ((userServerMap.get(i).getEmail().equals(email)) && (userServerMap.get(i).getPass().equals(pass))) {
					valid = true;
					userserverinfo = userServerMap.get(i);
				}
			} while (!valid && iter.hasNext());
		}
		return userserverinfo;
	}

	/**
	 *
	 * Return the maximum userid to assign to a new user
	 *
	 * @return int
	 */
	public int maxUserid() {
		//if userServerMap is not empty ? (return maximum keyset(userids) + 1) : (else return 1)
		return ((!userServerMap.isEmpty()) ? (Collections.max(userServerMap.keySet())) : 1);
	}

	/**
	 *
	 * Return a specific user
	 *
	 * @param userid
	 * @return User
	 */
	public User getUser(int userid) {
		return ((userMap.containsKey(userid)) ? userMap.get(userid) : null);
	}

	/**
	 *
	 * Start a users session
	 *
	 * @param userid
	 */
	public void startSession(int userid) {
		sessions.put(userid, true);
	}

	/**
	 *
	 * Return whether the session of the user is active, if session doesn't
	 * exist returns false.
	 *
	 * @param userid
	 * @return boolean
	 */
	public boolean findSession(int userid) {
		return ((sessions.containsKey(userid)) ? sessions.get(userid) : false);
	}

	/**
	 *
	 * End a users session
	 *
	 * @param userid
	 */
	public void endSession(int userid) {
		sessions.put(userid, false);
	}
}
