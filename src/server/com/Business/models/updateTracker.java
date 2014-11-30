package server.com.Business.models;

import server.com.Business.exception.UpdateTrackerException;

/*
 * author: Venkata Prashant
 * Description: This class represents the message format for update tracker message.
 * 				Class variables are the arguments of the message.
 * 				toString function is overridden to customize the string message generated by the object
 * 				A constructor is defined to initialize an object from string message.
 * 
 * Version: 0.1
 */
public class updateTracker {
	
	private String filename;
	private long startBytes;
	private long endBytes;
	private String ipAddress;
	private int portNumber;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public long getStartBytes() {
		return startBytes;
	}
	public void setStartBytes(long startBytes) {
		this.startBytes = startBytes;
	}
	public long getEndBytes() {
		return endBytes;
	}
	public void setEndBytes(long endBytes) {
		this.endBytes = endBytes;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	
	/*
	 * Description: A peer invoked function to obtain a string message of the request.
	 * Version: 0.1
	 */
	@Override
	public String toString() {
		return "<updatetracker " + filename + " " + Long.toString(startBytes) + " "
                + Long.toString(endBytes) + " "
				+ ipAddress + " " + Integer.toString(portNumber) + ">";
	}
	
	/*
	 * Description: A server invoked constructor to initialize an object from the values in string message.
	 * Version: 0.1
	 */
	public updateTracker(String message) throws UpdateTrackerException
	{
		if(message != null)
		{
			message.replaceAll(">", " ");
			String[] messageSplit = message.split(" ");
			if(messageSplit.length == 6)
			{
				try
				{
					this.filename = messageSplit[1];
					this.startBytes = new Integer(messageSplit[2]);
					this.endBytes = new Integer(messageSplit[3]);
					this.ipAddress = messageSplit[4];
					this.portNumber = new Integer(messageSplit[5]);
					
					
				}
				catch(Exception e)
				{
					throw new UpdateTrackerException();
				}
			}
			else
			{
				throw new UpdateTrackerException();
			}
		}
		else
		{
			throw new UpdateTrackerException();
		}
	}
	
	public updateTracker()
	{
		
	}
	

}
