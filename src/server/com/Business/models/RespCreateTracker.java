package server.com.Business.models;

/*
 * author: Venkata Prashant
 * Description: This class represents the response format to file tracker creation message.
 * 				Response variable can either take succ, fail or err.
 * 				toString function is overridden to customize the string message generated by the object
 * 				A constructor is defined to decode the create tracker message from the string message.
 * 
 * Version: 0.1
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
	 * Description: A peer invoked function to determine if the response is success or not.
	 * 				true is returned if the message is received successfully.
	 * Version: 0.1
	 */
	public boolean isSuccess()
	{
		if(response == "succ")
			return true;
		else
			return false;
	}
	
	/*
	 * Description: A server invoked function to obtain the string message of the response generated
	 * Verion: 0.1
	 */
	@Override
	public String toString() {
		return "<createtracker " + response + ">";
	}

	/*
	 * Description: A peer invoked function to initialize the object from the received message.
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
