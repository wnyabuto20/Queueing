import java.util.ArrayList;
/**
 * Server object; simulates a server
 */
public class Server{
    ArrayList <Job> currentJob;
    Job inService;
    public Server(){
        currentJob = new ArrayList<Job>();
    }
    public void addJob(Job nextJob){
        currentJob.add(nextJob);
        inService = nextJob;
    }
    public boolean isIdle(){
        if(currentJob.isEmpty()){
            return true;
        }
        return false;
    }
    public void completeJob(){
        currentJob.clear();
    }
}