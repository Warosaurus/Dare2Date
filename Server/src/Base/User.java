package Base;

import java.io.Serializable;

/**
 *
 * @author Warwick Louw
 */
public class User implements Serializable {

	private String fName;
	private String lName;
	private int userid;
	private int level;
	private int age;
	private String location;

	/**
	 *
	 * Default constructor
	 *
	 * Default level of user is 1, for guests.
	 * 
	 */
	public User() {
		level = 1;
	}

	/**
	 * Get id of user
	 *
	 * @return int
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * Get first name of user
	 *
	 * @return String
	 */
	public String getFName() {
		return this.fName;
	}

	/**
	 * Get last name of user
	 *
	 * @return String
	 */
	public String getLName() {
		return this.lName;
	}

	/**
	 * Get age of user
	 *
	 * @return int
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Get level of user
	 *
	 * @return int
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Get location of user
	 *
	 * @return String
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Set user id of user
	 *
	 * @param userid
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * Set first name of user
	 *
	 * @param fName
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}

	/**
	 * Set Last name of user
	 *
	 * @param lName
	 */
	public void setLName(String lName) {
		this.lName = lName;
	}

	/**
	 * Set age of user
	 *
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Set level of user
	 *
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Set location of user
	 *
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "User{" + "fName=" + fName + ", lName=" + lName + ", userid=" + userid + ", level=" + level + ", age=" + age + '}';
	}


}
