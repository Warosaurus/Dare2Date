package Base;

import java.util.Calendar;
import java.io.Serializable;
import java.util.Map;

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
	private Map preferencesMap;

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
	 *
	 * @param fName
	 * @param lName
	 * @param gender
	 * @param userid
	 * @param level
	 * @param age
	 * @param birthdate
	 * @param location
	 * @param preferencesMap
	 */
	public User(String fName, String lName, String gender, int userid, int level, int age, Calendar birthdate, String location, Map preferencesMap) {
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.userid = userid;
		this.level = level;
		this.age = age;
		this.birthdate = birthdate;
		this.location = location;
		this.preferencesMap = preferencesMap;
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
	 * Get the preferences of the user
	 *
	 * @return Map<String, ArrayList>
	 */
	public Map getPreferencesMap() {
		return preferencesMap;
	}

	/**
	 * Set the user id of the user
	 *
	 * @param userid int
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * Set the first name of the user
	 *
	 * @param fName String
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}

	/**
	 * Set the Last name of the user
	 *
	 * @param lName String
	 */
	public void setLName(String lName) {
		this.lName = lName;
	}

	/**
	 * Set the gender of the user
	 *
	 * @param gender String
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Set the age of the user
	 *
	 * @param age int
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Set the birthdate of the user
	 *
	 * @param birthdate Calendar
	 */
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Set the level of the user
	 *
	 * @param level int
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Set the location of the user
	 *
	 * @param location String
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Set the preferences of the user
	 *
	 * @param preferencesMap Map<String, ArrayList>
	 */
	public void setPreferencesMap(Map preferencesMap) {
		this.preferencesMap = preferencesMap;
	}

	@Override
	public String toString() {
		return "User{" + "fName=" + fName + ", lName=" + lName + ", gender=" + gender + ", userid=" + userid + ", level=" + level + ", age=" + age + ", birthdate=" + birthdate + ", location=" + location + '}';
	}
}
