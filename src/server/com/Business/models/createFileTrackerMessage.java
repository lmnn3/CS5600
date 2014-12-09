package server.com.Business.models;

import server.com.Business.exception.CreateTrackerException;

/*
 * Java object to represent the create file tracker message which is exchanged between server and client.
 */
public class createFileTrackerMessage {

	private String filename;
	private long fileBytes;
	private String description;
	private String checkSum;
	private String ipAddress;
	private int port;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFileBytes() {
		return fileBytes;
	}
	public void setFileBytes(long fileBytes) {
		this.fileBytes = fileBytes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	/*
	 * Overriden function to return a string message of the object.
	 */
	@Override
	public String toString() {
		return "<createtracker " + filename + " " + fileBytes + " " + description
				+ " " + checkSum + " " + ipAddress + " " + port + ">" ;
	}

    /*
     * constructor to initialize the object from a string message.
     */
	public createFileTrackerMessage(String message) throws CreateTrackerException {

		if(message != null)
		{
			message = message.replaceAll(">", " ");
			String[] messageSplit = message.split(" ");
			System.out.println(messageSplit[6]);
			if(messageSplit.length == 7)
			{
				try
				{
					this.filename = messageSplit[1];
					this.fileBytes = new Integer(messageSplit[2]);
					this.description = messageSplit[3];
					this.checkSum = messageSplit[4];
					this.ipAddress = messageSplit[5];
					this.port = new Integer(messageSplit[6]);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					throw new CreateTrackerException();
				}
			}
			else
			{
				throw new CreateTrackerException();
			}
		}
		else
		{
			throw new CreateTrackerException();
		}
	}


	public createFileTrackerMessage()
	{
		
	}

    /*
     * Constructor to initialize the objects based on attributes
     */
	public createFileTrackerMessage(String filename2, Long filesize,
			String description2, String md5, String my_ip, Integer my_port) {
		this.filename = filename2;
		this.fileBytes = filesize;
		this.description = description2;
		this.checkSum = md5;
		this.ipAddress = my_ip;
		this.port = my_port;
		
	}


}
