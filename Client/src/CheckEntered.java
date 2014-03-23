import java.util.Calendar;


public class CheckEntered {
	
	Calendar date;
	private Boolean ans;
	String problem = "the following items * were filled out incorrectly";
	
	public CheckEntered(){
		
	}
	
	public Boolean check(String enter){
		
		if((enter.length() < 24)&&(enter.length() > 0))
			ans = true;
		else
			ans = false;
		
		return ans;
	}
	
	public Boolean check(Calendar enter){
		
		Calendar date = Calendar.getInstance();
		int old = date.get(Calendar.YEAR) - enter.get(Calendar.YEAR);
		
		if(old >= 18)
			ans = true;
		else
			ans = false;
		
		return ans;
	}
	
	public Boolean check(String e_mail,String password){
		
		if(e_mail == "*'@'*")
			ans = true;
		if((password == "*"+0*9+"*"));
			ans = true;
		return ans;
	}
	
	public String toString(int number){
		
		return problem;
	}
	
	
}
