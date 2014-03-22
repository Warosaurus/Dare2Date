package Base;

import java.util.Calendar;


public class SignUp {

	private String firstName;
	private String surName;
	private Calendar age;
	private String gender;
	private String location;
	private String e_Mail;
	private String password;
	private String card_Name;
	private String account_Number;
	private String card_Number;

	
	@SuppressWarnings("unused")
	private SignUp(){
		
	}

	public SignUp(String first, String sur, Calendar ag, String gen, String loc, String e,	String pass, 
		String card_Nam, String account_Num, String card_Num){
		
			this.firstName = first;
			this.surName = sur; 
			this.age = ag;
			this.gender = gen;
			this.location = loc;
			this.e_Mail = e;
			this.password = pass;
			this.card_Name = card_Nam;
			this.account_Number = account_Num;
			this.card_Number = card_Num;
		
	}
	
	@Override
	public String toString() {
		return "SignUp [firstName=" + firstName + ", surName=" + surName
				+ ", age=" + age + ", gender=" + gender + ", location="
				+ location + ", e_Mail=" + e_Mail + ", password=" + "********"
				+ ", card_Name=" + card_Name + ", account_Number="
				+ account_Number + ", card_Number=" + card_Number + "]";
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

	public Calendar getAge() {
		return age;
	}

	public void setAge(Calendar age) {
		this.age = age;
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

	public String getCard_Name() {
		return card_Name;
	}

	public void setCard_Name(String card_Name) {
		this.card_Name = card_Name;
	}

	public String getAccount_Number() {
		return account_Number;
	}

	public void setAccount_Number(String account_Number) {
		this.account_Number = account_Number;
	}

	public String getCard_Number() {
		return card_Number;
	}

	public void setCard_Number(String card_Number) {
		this.card_Number = card_Number;
	}
	
}
