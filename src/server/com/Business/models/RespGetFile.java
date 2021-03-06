package server.com.Business.models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import server.com.Business.exception.CreateTrackerException;
import server.com.Business.exception.RespGetFileException;

/*
 * Java object to represent the response message for get file.
 */
public class RespGetFile {

	private String fileName;
	private long fileSize;
	private String description;
	private String md5;
	
	//contains an array of FilePeer objects. each FilePeer object represents one peer uploading the file.
	private ArrayList<FilePeers> peers;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public ArrayList<FilePeers> getPeers() {
		return peers;
	}
	public void setPeers(ArrayList<FilePeers> peers) {
		this.peers = peers;
	}
	/*
	 * Overriden function to return a string message of the object.
	 *
	 */
	@Override
	public String toString() {
		String filePeersString = "";
		for(Iterator<FilePeers> ite = peers.iterator(); ite.hasNext();)
		{
			FilePeers peerDetails = ite.next();
			filePeersString = filePeersString + "" + peerDetails.toString() + "\n";
		}
		String returnMessage = "Filename: " + fileName + "\nFilesize: " + fileSize + 
				"\nDescription: " + description + "\nMD5: " + md5 + "\n" + filePeersString;

		String trackerMd5 = this.getMd5(returnMessage);
		
		return "<REP GET BEGIN>\n" +returnMessage +"<REP GET END " + trackerMd5 + ">";
	}

    /*
     * Constructor to initialize an object from the string message.
     */
	 public String getMd5(String msg) {
	        String digest = null;
	        MessageDigest md = null;
	        try {
	            md = MessageDigest.getInstance("MD5");
	            byte[] hash = md.digest(msg.getBytes("UTF-8"));

	            StringBuilder sb = new StringBuilder(2 * hash.length);
	            for (byte b : hash) {
	                sb.append(String.format("%02x", b & 0xff));
	            }

	            digest = sb.toString();

	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return digest;
	    }

	/*
	 * constructor to initialize an object from a string message.
	 */
	public RespGetFile(String message) throws RespGetFileException
	{
		if(message != null)
		{
			String[] messageSplit = message.split(" ");
			try
			{
				this.fileName = messageSplit[1];
				this.fileSize = new Integer(messageSplit[2]);
				this.description = messageSplit[3];
				this.md5 = messageSplit[4];
				this.peers = new ArrayList<FilePeers>();
				
				for(int lcv = 5; lcv < messageSplit.length; lcv++)
				{
					FilePeers peerDetails = new FilePeers(messageSplit[lcv]);
					this.peers.add(peerDetails);
				}
			}
			catch(Exception e)
			{
				throw new RespGetFileException();
			}

		}
		else
		{
			throw new RespGetFileException();
		}
	}

	public RespGetFile()
	{
		
	}


}
