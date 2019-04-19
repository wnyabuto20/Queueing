import java.util.*;
import java.io.*;
import java.net.*;



public class Generator {
    double p;
    double mew1;
    double mew2;
    public Generator(){
        
    }
   // generates job sizes
    public double sizeDist(double mew, Random rand){
        double u = rand.nextDouble();
        double size = -(1.0/mew)*Math.log(1.0-u);
        return size;   
    }
    public void setp(double p){
        this.p =p;
    }
    public void setmew1(double mew1){
        this.mew1 = mew1;
    }
    public void setmew2(double mew2){
        this.mew2 = mew2;
    }

}