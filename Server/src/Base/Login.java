package Base;

/**
 *
 *
 */
public class Login {

	private String email;
	private String pass;

	/**
	 *
	 * Default constructor
	 * 
	 */
	public Login() {
	}

	/**
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @return String
	 */
	public String getPass() {
		return pass;
	}

	/**
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Login{" + "email=" + email + ", pass=" + pass + '}';
	}
}
