/**
 * Job object; keeps track of jobsizes and arrival times
 */
public class Job{
    double size;
    double arrivalTime;
    public Job(double size, double arrivalTime){
        this.size = size;
        this.arrivalTime = arrivalTime;
    }
    //sets jobsize
    public void setSize(double s){
        size = s;
    }
    //sets arrival time
    public void setArrivalTime(double a){
        arrivalTime = a;
    }
}