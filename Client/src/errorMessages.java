import javax.swing.JPanel;


public class errorMessages {
	
	String problem = "The folwing fields were filled out incorrectly";
	
	public errorMessages(){
		
	}
	
	public String errorText(JPanel pane){
		
		return problem;
	}
}
