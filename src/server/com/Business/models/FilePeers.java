package server.com.Business.models;

import java.util.Date;


import server.com.Business.exception.FilePeerException;

/*
 * Java object to represent a peer information present in the tracker file.
 */
public class FilePeers {
	
	private String ipAddress;
	private int portNumber;
	private long startByte;
	private long endByte;
	private Date timeStamp;
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
	public long getStartByte() {
		return startByte;
	}
	public void setStartByte(long startByte) {
		this.startByte = startByte;
	}
	public long getEndByte() {
		return endByte;
	}
	public void setEndByte(long endByte) {
		this.endByte = endByte;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	/*
	 * overriden function to return a string message of the object.
	 */
	@Override
	public String toString() {
		return "" + ipAddress + ":" + portNumber + ":" + startByte + ":" + endByte + ":" 
				+ timeStamp.getTime() + "";
	}
	
	/*
	 * Constructor to initialize java object from a string message
	 */
	public FilePeers(String message) throws FilePeerException {
		
		if(message != null)
		{
			String[] messageSplit = message.split(":");
			if(messageSplit.length == 5)
			{
				try
				{
					this.ipAddress = messageSplit[0];
					this.portNumber = new Integer(messageSplit[1]);
					this.startByte = new Integer(messageSplit[2]);
					this.endByte = new Integer(messageSplit[3]);
					this.timeStamp = new Date(messageSplit[4]);
					
				}
				catch(Exception e)
				{
					throw new FilePeerException();
				}
			}
			else
			{
				throw new FilePeerException();
			}
		}
		else
		{
			throw new FilePeerException();
		}
	}
	
	public FilePeers()
	{
		
	}
	
	

}
