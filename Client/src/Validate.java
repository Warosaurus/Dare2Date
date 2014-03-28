import java.util.Calendar;

import javax.swing.JLabel;


public class Validate {
	
	Calendar date = Calendar.getInstance();
	int old;
	
	public Validate(){
		
	}
	
	public Boolean onValidate(String firstname, String surname, String day, String month, String year, String gender, String town, JLabel first_error,
			JLabel sur_error, JLabel dob_error, JLabel gender_error, JLabel town_error){
		
		boolean ans = true;
		
		if(firstname.length() == 0){
			first_error.setVisible(true);
			ans = false;
		}
		else
			first_error.setVisible(false);
				
		if(surname.length() == 0){
			sur_error.setVisible(true);
			ans = false;
		}
		else
			sur_error.setVisible(false);
		
		
		if((day.length() == 2)&&(month.length() == 2)&&(year.length() == 4)){
			try{
				int number_day = Integer.parseInt(day);
				int number_month = Integer.parseInt(month);
				int number_year = Integer.parseInt(year);
				date.clear();
				date.set(number_year, number_month-1, number_day);
				date.setLenient(false);
				date.getTime();
				dob_error.setVisible(false);
				
				if(check(date)){
					dob_error.setText("*The user must age 18 or over");
					dob_error.setVisible(true);
					ans = false;
				}
				else
					dob_error.setVisible(false);
					
			}
			catch(Exception e){
				dob_error.setText("*The date entered is invalid");
				dob_error.setVisible(true);
				ans = false;
			}
		}
		else{
			dob_error.setVisible(true);
			ans = false;
		}
			
			
		if(gender == ""){
			gender_error.setVisible(true);
			ans = false;
		}
		else
			gender_error.setVisible(false);
		
		if(town.length() == 0){
			town_error.setVisible(true);
			ans = false;
		}
		else
			town_error.setVisible(false);
					
		return ans;
	}
	
	public Boolean onValidate(String email, String password, String accnumber, JLabel e_error, JLabel p_error, JLabel a_num_error){
		
		boolean ans = true;
		
		if(!email.contains("@")){
			e_error.setVisible(true);
			ans = false;
		}
		else
			e_error.setVisible(false);
			
		
		if(password == ""){
			p_error.setVisible(true);
			ans = false;
		}
		else
			p_error.setVisible(false);
		
		if(accnumber.length() != 16){
			a_num_error.setVisible(true);
			ans = false;
		}
		else
			a_num_error.setVisible(false);
					
		return ans;
	}
	
	public Boolean check(Calendar enter){
		
		Calendar check = Calendar.getInstance();
		old = check.get(Calendar.YEAR) - enter.get(Calendar.YEAR);
		boolean ans = false;
		
		if(old >= 18)
			ans = false;
		else
			ans = true;
		
		return ans;
	}
	
	public Calendar getCalendar(){
		
		return this.date;
	}
	
	public int getAge(){
		
		return this.old;
	}
}
