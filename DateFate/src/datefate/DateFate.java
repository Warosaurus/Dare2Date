package datefate;
import java.util.Scanner.*; //Temp for testing

public class DateFate {

	public static User[] addUsers(){
		java.util.Scanner scan = new java.util.Scanner(System.in);
		int userNum = 0;
		User[] useArr = new User[userNum];
		char cont = 'y';
		while(cont == 'y' || cont == 'Y'){
			System.out.println("First Name:");
			String newFName = scan.nextLine();
			System.out.println("Last Name:");
			String newLName = scan.nextLine();
			System.out.println("Password:");
			String newPass = scan.nextLine();
			System.out.println("Age:");
			int newAge = scan.nextInt();
			User newUser = new User(newFName,newLName,newPass,newAge);
			//Adapt the user array to fit the new user in.
			if (userNum == 0){
				useArr = new User[1];
				useArr[0] = newUser;
			} else {
				User[] tempArr = new User[userNum + 1];
				for (int i = 0; i < userNum - 1; i++){
					tempArr[i] = useArr[i];
				}
				tempArr[userNum] = newUser;
				useArr = new User[userNum + 1];
				for (int i = 0; i < userNum; i++){
					useArr[i] = tempArr[i];
				}
			}
			userNum++;
			System.out.print("Continue?");
			cont = scan.next().charAt(0);
			String emp = scan.nextLine();
		}
		return useArr;
	}
	
	
    public static void main(String[] args) {
		User[] users = addUsers();
		System.out.println(users.length);
    }
}
