import java.util.*;
import java.io.*;
/**
 * outputs results into csv files.
 * Output.csv for the main experiment and Output2.csv for the extension
 */
public class Main{
    public static void main(String[] args){
        try{
            File f = new File("Output.csv");
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            String header = "Lambda,Var1, Var10, Var20, Var50"+"\n";
            pw.write(header);
            System.out.println("Running ...");
            for(double i=0.1;i<=0.9;i+=0.1){
                Simulator var1 = new Simulator();
                double mrt1 = var1.simulate(i,0.5,1.0,1.0);
                Simulator var10 = new Simulator();
                double mrt10 = var10.simulate(i,0.9523,1.9045,0.0955);
                Simulator var20 = new Simulator();
                double mrt20 = var20.simulate(i, 0.9756,1.9512,0.0488);
                Simulator var50 = new Simulator();
                double mrt50 = var50.simulate(i,0.9901,1.9802,0.0198);
                String toWrite = i +","+ mrt1+"," + mrt10+"," + mrt20+","+ mrt50+"\n";
                pw.write(toWrite);
            }
            System.out.println("Done!! ");
            pw.flush();
		    fos.close();
            pw.close();
        }
        catch(IOException e) {
          e.printStackTrace();
        }
        try{
            File f2 = new File("Output2.csv");
            FileOutputStream fos2 = new FileOutputStream(f2);
            PrintWriter pw2 = new PrintWriter(fos2);
            String header2 = "Lambda,Var1, Var10, Var20, Var50"+"\n";
            pw2.write(header2);
            System.out.println("Running2 ...");
            for(double i=1.0;i<=1.9;i+=0.1){
                extSimulator va2r1 = new extSimulator();
                double mr2t1 = va2r1.simulate(i,0.5,1.0,1.0);
                extSimulator va2r10 = new extSimulator();
                double mr2t10 = va2r10.simulate(i,0.9523,1.9045,0.0955);
                extSimulator va2r20 = new extSimulator();
                double mr2t20 = va2r20.simulate(i, 0.9756,1.9512,0.0488);
                extSimulator va2r50 = new extSimulator();
                double mr2t50 = va2r50.simulate(i,0.9901,1.9802,0.0198);
                String toWrite2 = i +","+ mr2t1+"," + mr2t10+"," + mr2t20+","+ mr2t50+"\n";
                pw2.write(toWrite2);
            }
            System.out.println("Done2!! ");
            pw2.flush();
		    fos2.close();
            pw2.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}