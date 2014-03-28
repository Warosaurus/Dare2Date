import java.io.Serializable;
import java.util.Calendar;


public class SignUp implements Serializable{

	private String firstName;
	private String surName;
	private Calendar dob;
	private int age;
	private String gender;
	private String location;
	private String e_Mail;
	private String password;
	private String account_Number;

	
	@SuppressWarnings("unused")
	private SignUp(){
		
	}

	public SignUp(String first, String sur, Calendar DOB, int ag, String gen, String loc, String e,	String pass, String account_Num){
		
			this.firstName = first;
			this.surName = sur; 
			this.dob = DOB;
			this.age = ag;
			this.gender = gen;
			this.location = loc;
			this.e_Mail = e;
			this.password = pass;
			this.account_Number = account_Num;
		
	}
	
	@Override
	public String toString() {
		return "SignUp [firstName=" + firstName + ", surName=" + surName
				+ ", age=" + age + ", gender=" + gender + ", location="
				+ location + ", e_Mail=" + e_Mail + ", password=" + "********"
				+ ", account_Number="
				+ account_Number + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getE_Mail() {
		return e_Mail;
	}

	public void setE_Mail(String e_Mail) {
		this.e_Mail = e_Mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount_Number() {
		return account_Number;
	}

	public void setAccount_Number(String account_Number) {
		this.account_Number = account_Number;
	}
	
}
