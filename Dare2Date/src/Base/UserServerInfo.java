package Base;

import java.io.Serializable;

public class UserServerInfo extends User {
	private String email;
	private String pass;
	
	public UserServerInfo () {
	}

	public UserServerInfo(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}
	
	//set email of user
	public void setEmail(String email) {
		this.email = email;
	}
	
	//set password of user
	public void setPass(String pass){
		this.pass = pass;
	}
		
	//get first email of user
	public String getEmail(){
		return this.email;
	}
	
	//get password of user
	public String getPass(){
		return this.pass;
	}

	@Override
	public String toString() {
		return "UserServerInfo{" + super.toString() + "email=" + email + ", pass=" + pass + '}';
	}
}
