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
	private String account_Number;
	private String sexPref;
	private int level;
	private Map preferencesMap;
	
	@SuppressWarnings("unused")
	public SignUp(){
		
	}

	public SignUp(String firstName, String surName, Calendar birthdate, int age, String gender, String location, String e_Mail, String password, String account_Number, int level, Map map, String sexPref) {
		this.firstName = firstName;
		this.surName = surName;
		this.birthdate = birthdate;
		this.age = age;
		this.gender = gender;
		this.location = location;
		this.e_Mail = e_Mail;
		this.password = password;
		this.account_Number = account_Number;
		this.level = level;
		this.preferencesMap = map;
                this.sexPref = sexPref;
	}
	
	@Override
	public String toString() {
		return "SignUp{" + "firstName=" + firstName + ", surName=" + surName + ", birthdate=" + birthdate + ", age=" + age + ", gender=" + gender + ", location=" + location + ", e_Mail=" + e_Mail + ", password=" + password + ", account_Number=" + account_Number + ", level=" + level + ", preferencesMap=" + preferencesMap + '}';
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

	public String getSexPref() {
		return sexPref;
	}

	public void setSexPref(String sexPref) {
		this.sexPref = sexPref;
	}
        
        public String getAccountNumber(){
            return account_Number;
        }
        
        public void setAccountNumber(String account_Number){
            this.account_Number = account_Number;
        }

	public Map getPreferencesMap() {
		return preferencesMap;
	}

	public void setPreferencesMap(Map preferencesMap) {
		this.preferencesMap = preferencesMap;
	}
}
