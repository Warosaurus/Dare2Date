package datefate;

public class User {
	private String fName;
	private String lName;
	private String pass;
	private int age;

	User(){
	}

	public User(String newFName, String newLName, String newPass, int newAge){
		fName = newFName;
		lName = newLName;
		pass = newPass;
		age = newAge;
	}

    //set first name of user
    public void setFName(String newName){
		this.fName = newName;
    }

	//set Last name of user
    public void setLName(String newName){
		this.lName = newName;
    }

	//set Last name of user
    public void setPass(String newPass){
		this.pass = newPass;
    }

	//set Last name of user
    public void setAge(int newAge){
		this.age = newAge;
    }

	//get first name of user
	public String getFName(){
		return this.fName;
	}
	
	//get last name of user
	public String getLName(){
		return this.lName;
	}
	
	//get password of user
	public String getPass(){
		return this.pass;
	}
	
	//get age of user
	public int getAge(){
		return this.age;
	}
}
