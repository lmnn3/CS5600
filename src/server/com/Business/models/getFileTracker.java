package server.com.Business.models;

import server.com.Business.exception.GetFileTrackerMessageException;

/*
 * Java Object to represent the get file tracker message exchanged between server and client
 */
public class getFileTracker {
	
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/*
	 * Overriden function to return a string message of the java object
	 */
	@Override
	public String toString() {
		return "<GET " + fileName + ".track>\n";
	}
	
	/*
	 * constructor to initialize a java object from a string message
	 */
	public getFileTracker(String message) throws GetFileTrackerMessageException
	{
		if(message != null)
		{
			message = message.replaceAll(".track", " ");
			String[] messageSplit = message.split(" ");
			if(messageSplit.length == 3)
			{
				try
				{
					this.fileName = messageSplit[1];
				}
				catch(Exception e)
				{
					throw new GetFileTrackerMessageException();
				}
			}
		}
		else
		{
			throw new GetFileTrackerMessageException();
		}
	}
	
	
	public getFileTracker()
	{
		
	}

}
