import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


public class LimitField extends JTextField {
 
	 int limit;
	 
	 public LimitField(){
		 super();
	 }
	 
     public LimitField(int limit) {
    	 super();
    	 this.limit = limit;    	 
     }
     
     protected Document createDefaultModel() {
    	 
         return new LimitDocument();
     }

}