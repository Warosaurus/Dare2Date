package Base;

import java.io.Serializable;

public class Session implements Serializable {

	private int userid;
	private boolean active;
	
	public Session() {
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getUserid() {
		return userid;
	}

	public boolean isActive() {
		return active;
	}
	
	
}
