package Base;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

/**
 *
 *
 */
public class UserServerInfo extends User implements Serializable {

	private String email;
	private String pass;
	private String ccNumber;

	/**
	 * Default constructor
	 */
	public UserServerInfo() {
		super();
	}

	public UserServerInfo(String email, String pass, String ccNumber, String fName, String lName, String gender, int userid, int level, int age, Calendar birthdate, String location, Map preferencesMap) {
		super(fName, lName, gender, userid, level, age, birthdate, location, preferencesMap);
		this.email = email;
		this.pass = pass;
		this.ccNumber = ccNumber;
	}

	/**
	 * Constructor
	 *
	 * @param email
	 * @param pass
	 * @param ccNumber
	 */
	public UserServerInfo(String email, String pass, String ccNumber) {
		super();
		this.email = email;
		this.pass = pass;
		this.ccNumber = ccNumber;
	}

	/**
	 * Get the email of the user
	 *
	 * @return String
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Get the password of the user
	 *
	 * @return String
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * Get the credit card number of the user
	 *
	 * @return String
	 */
	public String getCcNumber() {
		return ccNumber;
	}

	/**
	 * Set the email of the user
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set the password of the user
	 *
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Set the credit card number of the user
	 *
	 * @param ccNumber
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	@Override
	public String toString() {
		return "UserServerInfo{" + super.toString() + "email=" + email + ", pass=" + pass + '}';
	}
}
