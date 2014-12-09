package server.com.Business.models;

/*
 * Java object to represent a create tracker response message exchanged between server and client
 */
public class RespCreateTracker 
{
	
	private String response;
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	/*
	 * returns a true if the message is "succ" or false otherwise.
	 */
	public boolean isSuccess()
	{
		if(response == "succ")
			return true;
		else
			return false;
	}
	
	/*
	 * Overriden function to return a string message of the object.
	 */
	@Override
	public String toString() {
		return "<createtracker " + response + ">";
	}

	/*
	 * constructor to initialize an object from a string message.
	 */
	public RespCreateTracker(String message) {
		
		try
		{
			String messageSplit[] = message.split(" ");
			this.response = messageSplit[1];
		}
		catch(Exception e)
		{
			this.response = "ferr";
		}
	}
	
	public RespCreateTracker()
	{
		
	}
	
	
	
	

}
