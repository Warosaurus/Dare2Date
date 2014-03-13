package Base;

import java.io.Serializable;

public class User implements Serializable {
	private String fName;
	private String lName;
	private int userid;
	private int level;
	private int age;

	public User() {
	}

	//Setter methods
	
	//set user id of user
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	//set first name of user
	public void setFName(String fName){
		this.fName = fName;
	}

	//set Last name of user
	public void setLName(String lName){
		this.lName = lName;
	}


	//set age of user
	public void setAge(int age){
		this.age = age;
	}

	//set level of user
	public void setLevel(int level){
		this.level = level;
	}
	
	//Getter methods
	
	//get id of user
	public int getUserid() {
		return userid;
	}

	//get first name of user
	public String getFName(){
		return this.fName;
	}

	//get last name of user
	public String getLName(){
		return this.lName;
	}

	//get age of user
	public int getAge(){
		return this.age;
	}

	//get level of user
	public int getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return "User{" + "fName=" + fName + ", lName=" + lName + ", userid=" + userid + ", level=" + level + ", age=" + age + '}';
	}

}
