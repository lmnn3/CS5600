package server.com.File.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Semaphore;

import server.com.Business.models.FileDetails;
import server.com.Business.models.RespList;
import server.com.File.Models.FileTracker;
import server.com.File.Models.PeerInfo;

public class Removal15Mins extends Thread
{
    public String classpath = new File("").getAbsolutePath();
    public String section;
    public long waitTime = 910000;
    private Semaphore sem;


    public Removal15Mins(Semaphore sem, String section) {
        this.sem = sem;
        this.section = section;
    }

    public void run()
    {
        File directory = new File(classpath + section);
        File[] files = directory.listFiles();
        FileTrackerModify ftModify = new FileTrackerModify(section);

        if(files != null)
        {

            for(File file : files)
            {
                try {
                    sem.acquire();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String fileName = file.getName();
                FileTracker tracker = ftModify.read(fileName);

                ArrayList<PeerInfo> peers = new ArrayList<PeerInfo>();

                tracker.setPeers(peers);
                ftModify.fileDelete(fileName);
                ftModify.write(tracker);

                sem.release();

            }

            try {
                this.sleep(waitTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        while(true)
        {
            directory = new File(classpath + section);
            files = directory.listFiles();
            if(files != null)
            {

                for(File file : files)
                {
                    try {
                        sem.acquire();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String fileName = file.getName();
                    FileTracker tracker = ftModify.read(fileName);

                    ArrayList<PeerInfo> peers = tracker.getPeers();
                    ArrayList<PeerInfo> removePeers = new ArrayList<PeerInfo>();
                    for(PeerInfo peer : peers)
                    {
                        long current_timestamp = new Date().getTime();

                        if((current_timestamp - peer.getTime()) >= waitTime)
                        {
                            removePeers.add(peer);
                        }
                    }

                    peers.removeAll(removePeers);

                    tracker.setPeers(peers);
                    ftModify.fileDelete(fileName);
                    ftModify.write(tracker);

                    sem.release();

                }

                try {
                    this.sleep(waitTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
