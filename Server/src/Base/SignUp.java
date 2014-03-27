package Base;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

public class SignUp implements Serializable {

	private String firstName;
	private String surName;
	private Calendar birthdate;
	private int age;
	private String gender;
	private String location;
	private String e_Mail;
	private String password;
	private String card_Name;
	private String account_Number;
	private String card_Number;
	private int level;
	private Map preferencesMap;
	
	@SuppressWarnings("unused")
	public SignUp(){
		
	}

	public SignUp(String firstName, String surName, Calendar birthdate, int age, String gender, String location, String e_Mail, String password, String card_Name, String account_Number, String card_Number, int level) {
		this.firstName = firstName;
		this.surName = surName;
		this.birthdate = birthdate;
		this.age = age;
		this.gender = gender;
		this.location = location;
		this.e_Mail = e_Mail;
		this.password = password;
		this.card_Name = card_Name;
		this.account_Number = account_Number;
		this.card_Number = card_Number;
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "SignUp{" + "firstName=" + firstName + ", surName=" + surName + ", birthdate=" + birthdate + ", age=" + age + ", gender=" + gender + ", location=" + location + ", e_Mail=" + e_Mail + ", password=" + password + ", card_Name=" + card_Name + ", account_Number=" + account_Number + ", card_Number=" + card_Number + ", level=" + level + ", preferencesMap=" + preferencesMap + '}';
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

	public Map getPreferencesMap() {
		return preferencesMap;
	}

	public void setPreferencesMap(Map preferencesMap) {
		this.preferencesMap = preferencesMap;
	}
}
