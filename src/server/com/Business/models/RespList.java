package server.com.Business.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import server.com.Business.models.FileDetails;

/*
 * Java Object to represent the response message for list.
 */
public class RespList {

    public static String RESP_END = "<REP LIST END>";
    public static Integer RESP_FIELDS = 4;

	//contains an array of FileDetails object. Each fileDetails object represents one file that is currently being shared.
	private ArrayList<FileDetails> fileDetails;

	public ArrayList<FileDetails> getFileDetails() {

		return fileDetails;
	}

    public String getFilenameAt(int i)
    {
       if( i < 0 || i >= fileDetails.size() )
       {
           System.out.println("Invalid file index. Must be between 0 and " + Integer.toString(fileDetails.size()));
           return null;
       }
       else
       {
          return fileDetails.get(i).getFileName();
       }
    }

	public void setFileDetails(ArrayList<FileDetails> fileDetails) {
		this.fileDetails = fileDetails;
	}
	/*
	 * Description: A server invoked function to represent the object as string message.
	 * Version: 0.1
	 */
	@Override
	public String toString() {
		String fileDetailsString = "<REP LIST " + Integer.toString(fileDetails.size()) + ">\n";
        Integer i = 1;
		for(Iterator<FileDetails> ite = fileDetails.iterator();ite.hasNext();)
		{
			FileDetails file = ite.next();
			fileDetailsString = fileDetailsString + "<" + i.toString() + " " + file.toString() + ">\n";
            i++;
		}
		return fileDetailsString + RESP_END;
	}

    public void print()
    {
        if( fileDetails != null )
        {
            System.out.println("Available Files");
            System.out.println("===============");
            Integer i = 1;
            FileDetails deets;
            for(Iterator<FileDetails> it = fileDetails.iterator(); it.hasNext(); )
            {
                deets = it.next();
                System.out.println(i.toString() + " " + deets.toString());
                i++;
            }
            System.out.println("===============");
        }
    }
	
	/*
	 * Description: A peer invoked constructor to initialize the obect with values obained from the string message.
	 * Version:0.1
	 */
	public RespList(List<String> message)
	{
		if(message != null)
		{
            this.fileDetails = new ArrayList<FileDetails>();
            int i = 0;
            int last = message.size() - 1;
            for(Iterator<String> it = message.iterator(); it.hasNext(); )
            {
                String msg = it.next();
                if( i != 0 && i != last ) {
                    String[] tokens = msg.split(" ");
                    if (tokens.length != RESP_FIELDS) {
                        System.out.println("Received unexpected number of fields in LIST response");
                        System.exit(1);
                    }
                    String filename = tokens[1];
                    String filesize = tokens[2];
                    String checksum = tokens[3].substring(0, tokens[3].length() - 1);

                    FileDetails details = new FileDetails(filename, filesize, checksum);
                    fileDetails.add(details);
                }
               i++;
            }
		}
	}
	
	public RespList()
	{
		
	}
	
}
