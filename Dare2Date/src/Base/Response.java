package Base;

public class Response {
	private Object response;
	private String error;
	
	public Response() {
	}
	
	public void setResponse(Object object) {
		this.response = object;
	}
	
	public void setError(String errmsg) {
		this.error = errmsg;
	}
	
	public Object getResponse() {
		return this.response;
	}
	
	public String getError() {
		return this.error;
	}
}
