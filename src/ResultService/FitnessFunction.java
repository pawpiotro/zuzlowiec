package ResultService;

import java.util.ArrayList;
import IndividualService.Individual;
/**
 * Created by blank on 1/10/2017.
 */


public class FitnessFunction {

    private Individual x;
    double upperLimit;
    double lowerLimit;
    private double[] v = new double[2];
    private Coordinates currentCoords;
    private ArrayList<Coordinates> coords = new ArrayList<>();

    class Coordinates   {
        private double r;
        private double phi;

        Coordinates(double r1, double phi1){
            r = r1;
            phi = phi1;
        }

        public double getPhi() {
            return phi;
        }

        public double getR() {
            return r;
        }
    }

    public FitnessFunction(Individual x1, int size){
        double scale = size/(2.0*40.0);
        double initialR = 35.0*scale;
        upperLimit = 40.0*scale;
        lowerLimit = 30.0*scale;
        System.out.println("initalR = "+initialR);
        currentCoords = new Coordinates(initialR,0);
        x = x1;
        this.v[0] = 0;
        this.v[1] = 0;
    }


    private void saveCoords(){
        Coordinates tmp = new Coordinates(currentCoords.r,currentCoords.phi);
        coords.add(tmp);
    }


    private void makeStep(double dt){
        double a0[] = x.getA0();
        double B[][] = x.getB();
        double c[] = x.getC();
        double a_r = a0[0] + B[0][0]*v[0] + B[0][1]*v[1] + c[0]*currentCoords.r;
        //System.out.println("a_r = "+a_r);
        double dv_r = a_r * dt;
        v[0] += dv_r;
        double dr = v[0] * dt;
        currentCoords.r += dr;
        double a_phi = a0[1] + B[1][0]*v[0] + B[1][1]*v[1] + c[1]*currentCoords.r;
        double dv_phi = a_phi * dt;
        v[1] += dv_phi;
        double dphi = (v[1] * dt);// / currentCoords.r;
        currentCoords.phi += dphi;
    }

    public double testParameters() {
        double dt = 0.001;
        double time = 0.0;
        for (;;) {
            makeStep(dt);
            saveCoords();
            /*System.out.println( "v[0]= " + v[0] + "\n" +
                                "v[1]= " + v[1] + "\n" +
                                "r   = " + currentCoords.r + "\n" +
                                "phi = " + currentCoords.phi + "\n");*/
            if (currentCoords.phi > 2*Math.PI) break;

            if(currentCoords.r > upperLimit || currentCoords.r < lowerLimit) break;

            time += dt;
        }
        double distance = currentCoords.phi/(2*Math.PI);
        if(distance > 1.0)
                distance = 1.0;
        System.out.println("Distance: "+distance);
        System.out.println("Time: "+ time);
        return ((distance*distance)/time) * (distance == 1.0 ? 1.0 : 0.7);
    }

    public ArrayList<Coordinates> getCoords(){
        return coords;
    }

    public void printarray(){
        for(Coordinates c: coords){
            System.out.println(c.getR()+" "+c.getPhi());
        }
    }
}

