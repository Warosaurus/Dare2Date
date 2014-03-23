import java.io.Serializable;

public class OnResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8382372512880581830L;
	private Object response;
	private String error;

	public OnResponse() {
	}

	//set the reponse object of the reponse
	public void setResponse(Object object) {
		this.response = object;
	}

	//set the error of the reponse
	public void setError(String errmsg) {
		this.error = errmsg;
	}

	//get the reponse object of the reponse
	public Object getResponse() {
		return this.response;
	}

	//get the error of the reponse
	public String getError() {
		return this.error;
	}

	@Override
	public String toString() {
		return "Response{" + "response=" + response + ", error=" + error + '}';
	}
}