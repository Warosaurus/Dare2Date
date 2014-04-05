/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


public class LimitField extends JTextField {
 
	 int limit;
	 
	 public LimitField(){
		 super();
	 }
	 
	 public LimitField(String text){
		 super(text);
	 }
	 
     public LimitField(int limit) {
    	 super();
    	 this.limit = limit;    	 
     }
     
     protected Document createDefaultModel() {
    	 
         return new LimitDocument();
     }

}