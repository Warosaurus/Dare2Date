package Server;

import Base.*;

import java.util.Map;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Warwick Louw
 */
public class ServerImpl extends UnicastRemoteObject implements ServiceInterface {

	private final ConcurrentHashMap<Integer, User> userMap;
	private final ConcurrentHashMap<Integer, Boolean> sessions;
	private final ConcurrentHashMap<Integer, UserServerInfo> userServerMap;
	private final ConcurrentHashMap<Integer, ArrayList> userMatches;
	private final ConcurrentHashMap<Integer, String> clientIps;

	/**
	 *
	 * @throws java.rmi.RemoteException Default constructor.
	 */
	public ServerImpl() throws RemoteException {
		userMap = new ConcurrentHashMap();
		sessions = new ConcurrentHashMap();
		userServerMap = new ConcurrentHashMap();
		userMatches = new ConcurrentHashMap();
		clientIps = new ConcurrentHashMap();
	}

	/**
	 *
	 * @param signUp
	 * @return Response - boolean
	 * @throws RemoteException
	 */
	@Override
	public Response SignUp(SignUp signUp) throws RemoteException {
		Response res = new Response();
		//possible refator, check if hashmap is empty, otherwise check email address. reduce calls.
		if (!existingEmail(signUp.getE_Mail())) {//check if it is a vlid email address
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
			System.out.println(userMap);
		}
		return res;
	}

	/**
	 *
	 * @param email
	 * @param pass
	 * @return Response - User
	 * @throws RemoteException
	 */
	@Override
	public Response Login(String email, String pass) throws RemoteException {
		Response res = new Response();
		int userid = checkLoginDetails(email, pass);
		if (userid != 0) {
			User user = getUser(userid);
			startSession(userid);
			res.setResponse(user);
		} else {
			res.setError("email and password combination does not match.");
		}
		return res;
	}

	/**
	 *
	 * @param userid
	 * @return Response - boolean
	 */
	@Override
	public Response Logoff(int userid) {
		Response res = new Response();
		endSession(userid);
		clientIps.remove(userid);
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
	public int checkLoginDetails(String email, String pass) {
		//UserServerInfo userserverinfo = new UserServerInfo();
		//boolean valid = false;
		int userid = 0;
		//if there are no users then there is no point in checking if a specific email address is registerd.
		if (!userServerMap.isEmpty()) {
			Iterator<Integer> iter = userServerMap.keySet().iterator();
			do {
				Integer i = iter.next();
				if ((userServerMap.get(i).getEmail().equals(email)) && (userServerMap.get(i).getPass().equals(pass))) {
					//valid = true;
					userid = i;
				}
			} while ((userid == 0) && iter.hasNext());
		}
		return userid;
	}

	/**
	 *
	 * Return the maximum userid
	 *
	 * @return int
	 */
	public int maxUserid() {
		//if userServerMap is not empty ? (return maximum keyset(userids)) : (else return 0)
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
	 * @param signUp
	 * @param userid
	 * @return Response - boolean
	 */
	@Override
	public Response UpdateUser(SignUp signUp, int userid) {
		Response res = new Response();
		if (userMap.containsKey(userid)) {
			User user = new User(signUp.getFirstName(), signUp.getSurName(), signUp.getGender(), userid, signUp.getLevel(), signUp.getAge(), signUp.getBirthdate(), signUp.getLocation(), signUp.getPreferencesMap(), signUp.getSexPref());
			//create a UserServerInfo object based on the SignUp object
			UserServerInfo userserverinfo = new UserServerInfo(signUp.getE_Mail(), signUp.getAccountNumber(), signUp.getPassword(), signUp.getFirstName(), signUp.getSurName(), signUp.getGender(), userid, signUp.getLevel(), signUp.getAge(), signUp.getBirthdate(), signUp.getLocation(), signUp.getPreferencesMap(), signUp.getSexPref());
			//place the userserverinfo object into the hashmap
			userServerMap.put(userid, userserverinfo);
			//place the user object into the the hashmap
			userMap.put(userid, user);
			res.setResponse(true);
		} else {
			res.setResponse(false);
			res.setError("User does not exist.");
		}
		return res;
	}

	/**
	 *
	 * -Depreciated Search for a person based on their first name or last name.
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
	@Override
	public Response search(Map<String, ArrayList> map) {
		Response res = new Response();
		ArrayList<User> userArr = new ArrayList();
		res.setResponse(userArr);
		if (!map.isEmpty() && !userMap.isEmpty()) {
			Iterator<Integer> iter = userMap.keySet().iterator();
			while (iter.hasNext()) {
				Integer i = iter.next();
				for (String key : map.keySet()) {
					//first check names, first and last.
					if (key.equals("Fname") || key.equals("Lname")) {
						if (userMap.get(i).getFName().matches(map.get(key).toString()) || userMap.get(i).getLName().matches(map.get(key).toString())) {
							userArr.add(userMap.get(i));
						}
					}
					//First check if the current user has set that preference.
					else if (userMap.get(i).getPreferencesMap().containsKey(key)) {
						//Then check to see if the current user has this value in their preferences.
						for (int x = 0; x < map.get(key).size(); x++) {//maybe edit map.get(cat).values().size()
							if (userMap.get(i).getPreferencesMap().get(key).contains(map.get(key).get(x))) {
								if (!userArr.contains(userMap.get(i))) {
									userArr.add(userMap.get(i));
								}
							}
						}
					}
				}

			}
			if (!userArr.isEmpty())
				res.setResponse(userArr);
			else
				res.setError("No results.");
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
	 * @param map Map<String, ArrayList>
	 * @return Response - User
	 */
	@Override
	public Response selectionMatch(User user, Map<String, ArrayList> map) {
		Response res = new Response();
		if (user.getLevel() == 2) {
			if (!userMap.isEmpty() && !map.isEmpty()) {
				String cat = map.keySet().toString();
				Iterator<Integer> iter = userMap.keySet().iterator();
				while (iter.hasNext()) {
					Integer i = iter.next();
					//check to see if they have been matched previously.
					if (!userMatches.get(user.getUserid()).contains(userMap.get(i))) {
						//check if they are interested in each other.
						if (userMap.get(i).getGender().equals(user.getSexPref()) && userMap.get(i).getSexPref().equals(user.getGender())) {
							//check to see if the current user has a preference in this field.
							if (userMap.get(i).getPreferencesMap().containsKey(cat)) {
								//Then check to see if the current user has this value in their preferences.
								for (int x = 0; x < map.get(cat).size(); x++) {
									if (userMap.get(i).getPreferencesMap().get(cat).contains(map.get(cat).get(x))) {
										res.setResponse(userMap.get(i));//we are done here.
									}
								}
							}
						}
					}
				}
				if (res.getResponse() == null) {
					res.setError("Sorry there are no matches right now.");
				}
			} else {
				res.setError("Sorry there are no matches right now.");
			}
		} else {
			res.setError("You do not have sufficiant rights to perform this action.");
		}
		return res;
	}

	/**
	 *
	 * Match users based a certain criteria for VIP users
	 *
	 * @param user User
	 * @return Response - User
	 */
	@Override
	public Response criteriaMatch(User user) {
		Response res = new Response();
		Map<Integer, Integer> ranking = new HashMap();
		if (user.getLevel() == 2) {
			if (!userMap.isEmpty()) {
				Iterator<Integer> iter = userMap.keySet().iterator();
				while (iter.hasNext()) {
					Integer i = iter.next();
					//check if these people have been matched previously.
					if (!userMatches.get(user.getUserid()).contains(userMap.get(i).getUserid())) {
						//check if they are interested in each other.
						if (user.getSexPref().equals(userMap.get(i).getGender()) && userMap.get(i).getSexPref().equals(user.getGender())) {
							int rank = 0;//rank will be used to determine the best match
							//if they have the same location then rank +1
							rank += (userMap.get(i).getLocation().equalsIgnoreCase(user.getLocation())) ? 1 : 0;
							//if they are the same age then rank +1
							rank += (userMap.get(i).getAge() == user.getAge()) ? 1 : 0;
							//now check their preferences map. for each category
							for (String cat : user.getPreferencesMap().keySet()) {
								for (int x = 0; i < user.getPreferencesMap().get(cat).size(); i++) {
									rank += (userMap.get(i).getPreferencesMap().get(cat).contains(user.getPreferencesMap().get(cat).get(x))) ? 1 : 0;
								}
							}
							//now place the user in the map.
							ranking.put(userMap.get(i).getUserid(), rank);
						}
					}
				}
			}
		}
		int id = 0;
		int max = 0;
		//get the highest rank.
		for (int i : ranking.keySet()) {
			if (ranking.get(i) > max) {
				max = ranking.get(i);
				id = i;
			}
		}
		res.setResponse(getUser(ranking.get(id)));
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
	 * Get all of the currently online users
	 *
	 * @param user
	 * @return Response - User
	 */
	@Override
	public Response getOnlineUsers(User user) {
		ArrayList<User> onlineUsers = new ArrayList();
		Response res = new Response();
		if (!sessions.isEmpty() && !userMap.isEmpty()) {
			Iterator<Integer> iter = userMap.keySet().iterator();
			while (iter.hasNext()) {
				Integer i = iter.next();
				if (sessions.get(userMap.get(i).getUserid())) {
					if (userMap.get(i).getUserid() != i) {
						onlineUsers.add(userMap.get(i));
					}
				}
			}
			if (!onlineUsers.isEmpty())
				res.setResponse(onlineUsers);
			else
				res.setError("There are no other users logged in.");
		} else {
			res.setError("There are no other users logged in!");
		}
		return res;
	}

	/**
	 *
	 * Get all of the currently online users
	 *
	 * @param mail
	 */
	@Override
	public void sendMail(Mail mail) {
		try {
			String ip = clientIps.get(mail.getReciever().getUserid());
			ClientInterface chat = (ClientInterface) Naming.lookup(ip);
			chat.recieveMail(mail);
		} catch (MalformedURLException | NotBoundException | RemoteException | NullPointerException e) {
			System.out.println(e);
		}
	}

	/**
	 *
	 * Get all of the currently online users
	 *
	 * @param ip String
	 * @param user User
	 * @return Response - boolean
	 */
	@Override
	public Response setClientRmi(String ip, User user) {
		Response res = new Response();
		clientIps.put(user.getUserid(), ip);
		res.setResponse(true);
		return res;
	}
}
