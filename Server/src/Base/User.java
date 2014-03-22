package Base;

import java.util.Calendar;
import java.io.Serializable;

/**
 *
 * 
 */
public class User implements Serializable {

	private String fName;
	private String lName;
	private String gender;
	private int userid;
	private int level;
	private int age;
	private Calendar birthdate;
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
	 *
	 * Constructor
	 * @param fName
	 * @param lName
	 * @param gender
	 * @param userid
	 * @param level
	 * @param age
	 * @param birthdate
	 * @param location
	 */
	public User(String fName, String lName, String gender, int userid, int level, int age, Calendar birthdate, String location) {
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.userid = userid;
		this.level = level;
		this.age = age;
		this.birthdate = birthdate;
		this.location = location;
	}

	/**
	 * Get the id of the user
	 *
	 * @return int
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * Get the first name of the user
	 *
	 * @return String
	 */
	public String getFName() {
		return this.fName;
	}

	/**
	 * Get the last name of the user
	 *
	 * @return String
	 */
	public String getLName() {
		return this.lName;
	}

	/**
	 * Get the gender of the user
	 *
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Get the age of the user
	 *
	 * @return int
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Get the birthdate of the user
	 *
	 * @return int
	 */
	public Calendar getBirthdate() {
		return birthdate;
	}

	/**
	 * Get the level of the user
	 *
	 * @return int
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Get the location of the user
	 *
	 * @return String
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Set the user id of the user
	 *
	 * @param userid
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * Set the first name of the user
	 *
	 * @param fName
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}

	/**
	 * Set the Last name of the user
	 *
	 * @param lName
	 */
	public void setLName(String lName) {
		this.lName = lName;
	}

	/**
	 * Set the gender of the user
	 *
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Set the age of the user
	 *
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Set the birthdate of the user
	 *
	 * @param birthdate
	 */
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Set the level of the user
	 *
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Set the location of the user
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
