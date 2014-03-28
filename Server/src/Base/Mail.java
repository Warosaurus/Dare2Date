package Base;

/**
 *
 */
public class Mail {

	private int sender;
	private int reciever;
	private String content;
	
	public Mail() {
	}

	public Mail(int sender, int reciever, String content) {
		this.sender = sender;
		this.reciever = reciever;
		this.content = content;
	}

	public int getSender() {
		return sender;
	}

	public int getReciever() {
		return reciever;
	}

	public String getContent() {
		return content;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public void setReciever(int reciever) {
		this.reciever = reciever;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
