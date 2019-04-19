import java.util.*;
/**
 * uses exponentially distributed job sizes and changing variability of interarrival times.
 * (essentially switches how interarival times and job sizes are generated in Simulator.java)
 */
public class extSimulator{
    private double nextArrival;
    private double nextDeparture;
    private double currentTime = 0.0;
    private double lambda;
    private Server server;
    private int numberOfJobs = 1000000;
    Generator generator;
    Queue<Job> jobQueue;
    Random random = new Random(); //this instance will be used throught.
    public extSimulator(){
        jobQueue = new LinkedList<Job>();
        server = new Server();
        generator = new Generator();
    }
   // Generates interarrival times 
    public double generateJobs(){
        Random rand = new Random();
        double u = rand.nextDouble();
        double time = -(1.0/lambda)*Math.log(1.0-u);
        return time;
    }

    public void setLambda(double newlambda){
        lambda = newlambda;
    }
    public void setnumberOfJobs(int n){
        numberOfJobs = n;
    }
    public double interArrivalTimes(double p1, double mew3, double mew4){
        //with probability p generate job sizes X = exp(mew1) and with probability 1-px = exp(mew1)
        double prob = random.nextDouble();
        double jobtoAd = 0.0;
        if(prob <= p1){
            jobtoAd = generator.sizeDist(mew3, random);
        }
        if(prob > p1){
            jobtoAd = generator.sizeDist(mew4,random);
        }
        return jobtoAd;
    }
    /** takes in the parameters given and outputs an arraylist of response times.
     * mean response time can be found by getting the average of these response times.
     * @param lambda1
     * @param p
     * @param mew1
     * @param mew2
     * @return
     */
    public double simulate(double lambda1,double p, double mew1, double mew2){
        ArrayList<Double> responseTimes = new ArrayList<Double>();
        double sumOfResponseTimes = 0.0;
        double meanResponseTime = 0.0;
        setLambda(lambda1);
        //set the expected arrival of the first job
        nextArrival = currentTime + interArrivalTimes(p, mew1, mew2);
        double jobSizeToAdd = 0.000000000000000;
        generator.setp(p);
        generator.setmew1(mew1);
        generator.setmew2(mew2);
        for(int i =0;i<numberOfJobs;i++){
            //initialize a job
            Job job = new Job(0.0, 0.0);
        /**
         * If the next arrival time is earlier than the next departure time
         * 
         */
            if(nextArrival < nextDeparture){
                //Advance the current system time to the next arrival time
                currentTime = nextArrival;
                double arr = currentTime;
                //generate a job from an exponential distribution
                jobSizeToAdd = generateJobs();
                job = new Job(jobSizeToAdd, arr);
                
                //start processing the job if the server is idle
                if(server.isIdle()){
                    server.addJob(job);
                    nextDeparture = currentTime + job.size;
                    /**
                     * at this point, next departure time is the same as the time this job will end. We can calculate
                     * response time since each job has an arrival time.
                     * 
                     */
                }
                //add job to queue if server is busy
                if(!server.isIdle()){
                    jobQueue.add(job);
                 }
                 //setting up next arrival;
                 nextArrival += interArrivalTimes(p, mew1, mew2);;
            } 

            /**
             * 
             * if the next departure time is earlier than the next arrival time
             * 
             */
            if(nextArrival > nextDeparture){
                currentTime = nextDeparture;
                //complete the job being processed
                Job tocomplete = server.inService;
                //throwing away the first 10000
                if(i>10000){
                    responseTimes.add(currentTime - tocomplete.arrivalTime);
                    sumOfResponseTimes += (currentTime - tocomplete.arrivalTime);
                }
                server.completeJob();

                //get a job from the queue
                if(!jobQueue.isEmpty()){
                    Job next = jobQueue.poll();
                    System.out.println(next);
                    server.addJob(next);
                    nextDeparture = currentTime + next.size;
                 }
                 /**
                  * if the queue is empty (having completed the job in the server)
                  */
                 else{
                    //generate a new job and start processing it/add it to the server.
                    jobSizeToAdd = generateJobs();
                    double arr1 = currentTime;
                    job = new Job(jobSizeToAdd, arr1);
                    server.addJob(job);
                    nextDeparture = currentTime + job.size;
                 }
            }
        }
        meanResponseTime = (sumOfResponseTimes/990000);
        return meanResponseTime;
    }

}