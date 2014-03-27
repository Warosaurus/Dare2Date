import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class LimitDocument extends PlainDocument {
	
	int limit = 18;
	
	public LimitDocument(){
		super();
	}
	
	public LimitDocument(int limit){
		super();
		this.limit = limit;
	}
	
	public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

            if (str == null) {
                return;
            }
            
            if (str.length()+getLength() <= this.limit){
            	super.insertString(offs, str , a);
            }
	}
}
