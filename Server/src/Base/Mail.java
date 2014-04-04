package Base;

import java.io.Serializable;
/**
 *
 */
public class Mail implements Serializable {

	private User sender;
	private User reciever;
	private String content;
	
	public Mail() {
	}

	public Mail(User sender, User reciever, String content) {
		this.sender = sender;
		this.reciever = reciever;
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public User getReciever() {
		return reciever;
	}

	public String getContent() {
		return content;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
