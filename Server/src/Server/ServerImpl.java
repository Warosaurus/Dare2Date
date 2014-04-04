package Server;

import Base.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Collection;

/**
 *
 *
 */
public class ServerImpl extends UnicastRemoteObject implements ServiceInterface {

	private final ConcurrentHashMap<Integer, User> userMap;
	private final ConcurrentHashMap<Integer, Boolean> sessions;
	private final ConcurrentHashMap<Integer, UserServerInfo> userServerMap;
	private final ConcurrentHashMap<Integer, ArrayList> userMatches;

	/**
	 *
	 * @throws java.rmi.RemoteException Default constructor.
	 */
	public ServerImpl() throws RemoteException {
		userMap = new ConcurrentHashMap();
		sessions = new ConcurrentHashMap();
		userServerMap = new ConcurrentHashMap();
		userMatches = new ConcurrentHashMap();
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
		System.out.println(signUp.getPreferencesMap().keySet());
		//possible refator, check if hashmap is empty, otherwise check email address. reduce calls.
		if (!existingEmail(signUp.getE_Mail())) {
			res.setError("This email address has already been registerd.");
		} else {
			//return the max userid so that the next one can be assigned to a new user
			int maxid = maxUserid();
			int userid = (maxid == 0) ? 1 : maxid + 1;
			User user = new User(signUp.getFirstName(), signUp.getSurName(), signUp.getGender(), userid, signUp.getLevel(), signUp.getAge(), signUp.getBirthdate(), signUp.getLocation(), signUp.getPreferencesMap(), signUp.getSexPref());
			//create a UserServerInfo object based on the SignUp object
			UserServerInfo userserverinfo = new UserServerInfo(signUp.getE_Mail(), signUp.getAccountNumber(), signUp.getPassword(), signUp.getFirstName(), signUp.getSurName(), signUp.getGender(), userid, signUp.getLevel(), signUp.getAge(), signUp.getBirthdate(), signUp.getLocation(), signUp.getPreferencesMap(), signUp.getSexPref());
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
	 *
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
			while (iter.hasNext() && valid == true) {
				Integer i = iter.next();
				if (userServerMap.get(i).getEmail().equals(email)) {
					valid = false;
				}
			}
		}
		return valid;
	}

	/**
	 *
	 * @param email String
	 * @param pass String
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
	 * Return the maximum userid
	 *
	 * @return int
	 */
	public int maxUserid() {
		//if userServerMap is not empty ? (return maximum keyset(userids) + 1) : (else return 1)
		return ((!userServerMap.isEmpty()) ? (Collections.max(userServerMap.keySet())) : 0);
	}

	/**
	 *
	 * Return a specific user
	 *
	 * @param userid int
	 * @return User
	 */
	public User getUser(int userid) {
		return ((userMap.containsKey(userid)) ? userMap.get(userid) : null);
	}

	/**
	 *
	 * Return a list of all users of a specific preference - Note to self,
	 * possibly add bi-gender search
	 *
	 * @param gender String
	 * @return Response - ArrayList - User
	 */
	@Override
	public Response viewProfiles(String gender) {
		Response res = new Response();
		ArrayList<User> userlist = new ArrayList();
		if (!userMap.isEmpty()) {
			Iterator<Integer> iter = userMap.keySet().iterator();
			while (iter.hasNext()) {
				Integer i = iter.next();
				if (userMap.get(i).getGender().equals(gender)) {
					userlist.add(userMap.get(i));
				}
			}
		}
		res.setResponse(userlist);
		return res;
	}

	/**
	 *
	 * Search for a person based on their first name or last name.
	 *
	 * @param keyword String
	 * @return Response - ArrayList - User
	 */
	@Override
	public Response nameSearch(String keyword) {
		Response res = new Response();
		if (keyword != null) {
			ArrayList<User> userlist = new ArrayList();
			if (!userMap.isEmpty()) {
				Iterator<Integer> iter = userMap.keySet().iterator();
				while (iter.hasNext()) {
					Integer i = iter.next();
					if (userMap.get(i).getFName().matches(keyword) || userMap.get(i).getLName().matches(keyword)) {
						userlist.add(userMap.get(i));
					}
				}
			}
			res.setResponse(userlist);
		} else {
			res.setError("No keyword supplied.");
		}
		return res;
	}

	/**
	 *
	 * Match users based on their location and gender preference
	 *
	 * @param map
	 * @return Response - ArrayList - User
	 */
	public Response search(Map<String, ArrayList> map) {
		Response res = new Response();
		ArrayList<User> userArr = new ArrayList();
		res.setResponse(userArr);
		String cat = map.keySet().toString();
		if (!map.isEmpty() && !userMap.isEmpty()) {
			Iterator<Integer> iter = userMap.keySet().iterator();
			while (iter.hasNext()) {
				Integer i = iter.next();
				Collection<ArrayList> mapVals = map.values();
				//First check if the current user has set that preference.
				if (userMap.get(i).getPreferencesMap().containsKey(cat)) {
					//Then check to see if the current user has this value in their preferences.
<<<<<<< HEAD
					//for (int x = 0; x < map.values().size(); x++) {
					if (userMap.get(i).getPreferencesMap().get(map.keySet()).contains(map.values())) {
						userArr.add(userMap.get(i));
=======
					for (int x = 0; x < map.values().size(); x++) {
						if (userMap.get(i).getPreferencesMap().get(cat).contains(map.get(cat).get(x))) {
							userArr.add(userMap.get(i));
						}
>>>>>>> ae33a72... Updated search again
					}
					//}
				}
			}
		} else {
			res.setError("no keyword specified");
		}
		return res;
	}

	/**
	 *
	 * Match users based on their location and gender preference
	 *
	 * @param user User
	 * @return Response - ArrayList - User
	 */
	@Override
	public Response blindLocationMatch(User user) {
		Response res = new Response();
		if (user.getLevel() > 1) {
			if (!userMap.isEmpty()) {
				Iterator<Integer> iter = userServerMap.keySet().iterator();
				while (iter.hasNext()) {
					Integer i = iter.next();
					//first chack if the locations match and their gender is what they are looking for and that the person in question is the gender type they are looking for.
					if (userMap.get(i).getLocation().equalsIgnoreCase(user.getLocation()) && userMap.get(i).getGender().equals(userMap.get(i).getSexPref()) && userMap.get(i).getSexPref().equals(user.getGender())) {
						//then check if the this user has matches, then check if these two users haven't been matched before.
						if (userMatches.containsKey(user.getUserid()) && !userMatches.get(user.getUserid()).contains(i)) {
							//if the criteria is matched then return this user
							res.setResponse(userMap.get(i));
						}
					}
				}
			}
		} else {
			res.setError("You do not have sufficiant rights to perform this action.");
		}
		return res;
	}

	/**
	 *
	 * Match users based on their age
	 *
	 * @param user User
	 * @return Response - ArrayList - User
	 */
	@Override
	public Response blindAgeMatch(User user) {
		Response res = new Response();
		if (user.getLevel() > 1) {
			if (!userMap.isEmpty()) {
				Iterator<Integer> iter = userServerMap.keySet().iterator();
				while (iter.hasNext()) {
					Integer i = iter.next();
					//first chack if the ages match and their gender is what they are looking for
					if ((userMap.get(i).getAge() == user.getAge()) && userMap.get(i).getGender().equals(user.getSexPref()) && userMap.get(i).getSexPref().equals(user.getGender())) {
						//then check if the this user has matches, then check if these two users haven't been matched before.
						if (userMatches.containsKey(user.getUserid()) && !userMatches.get(user.getUserid()).contains(i)) {
							//if the criteria is matched then return this user
							res.setResponse(userMap.get(i));
						}
					}
				}
			}
		} else {
			res.setError("You do not have sufficiant rights to perform this action.");
		}
		return res;
	}

	/**
	 *
	 * Match users based on a selection
	 *
	 * @param user User
	 * @return Response - User
	 */
	public Response selectionMatch(User user) {
		Response res = new Response();
		return res;
	}

	/**
	 *
	 * Match users based a certain criteria
	 *
	 * @param user User
	 * @return Response - User
	 */
	public Response criteriaMatch(User user) {
		Response res = new Response();
		return res;
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

	/**
	 *
	 * Get all of the currently online users -On todo list.
	 *
	 * @param user
	 * @return Response - User
	 */
	@Override
	public Response getOnlineUsers(User user) {
		ArrayList<User> onlineUsers = new ArrayList();
		Response res = new Response();
		System.out.println("error 5 :     " + sessions.toString());
		if (!sessions.isEmpty()) {
			for (int i = 1; i < sessions.size(); i++) {
				System.out.println("error 1 :     " + onlineUsers.toString());
				if (sessions.get(i)) {
					System.out.println("error 2 :  " + onlineUsers.toString());
					if (user.getUserid() != i) {
						onlineUsers.add(userMap.get(i));
					}
					System.out.println("error 3 :   " + onlineUsers.toString());
				}
			}
			System.out.print("error 4 :   " + onlineUsers.toString());
			res.setResponse(onlineUsers);
		} else {
			res.setError("There are no other users logged in!");
		}
		System.out.print(onlineUsers.toString());
		return res;
	}

}
