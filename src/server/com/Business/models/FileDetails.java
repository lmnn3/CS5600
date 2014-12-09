package server.com.Business.models;

/*
 * Java Object to represent a tracker file present on the server.
 */
public class FileDetails {

	private String fileName;
	private String fileSize;
	private String checkSum;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	/*
	 * Overriden function to return a string message from the object
	 */
	@Override
	public String toString() {
		return "" + fileName + " " + fileSize  + " " + checkSum + "";
	}
	
	/*
	 * Constructor to initialize a java object from a string message
	 */
	public FileDetails(String message)
	{
		if(message != null)
		{
			String[] messageSplit = message.split(" ");
			this.fileName = messageSplit[0];
			this.fileSize = messageSplit[1];
			this.checkSum = messageSplit[2];
			
		}
	}

    /*
     * Constructor to initialize a java object from class attributes
     */
    public FileDetails(String filename, String filesize, String checksum)
    {
        if( filename != null && filesize != null && checksum != null )
        {
            this.fileName = filename;
            this.fileSize = filesize;
            this.checkSum = checksum;
        }
    }
    
    public FileDetails()
    {
    	
    }
	
	
}

