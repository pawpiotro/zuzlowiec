package ResultService;

import java.util.ArrayList;

/**
 * Created by blank on 1/10/2017.
 */


public class FitnessFunction {
    private double[] a0 = new double[2];
    private double[][] B = new double[2][2];
    private double[] c = new double[2];
    private double[] v = new double[2];
    //private double[] x = new double[2];

    class Coordinates   {
        double r;
        double phi;

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

    private Coordinates currentCoords = new Coordinates(35,0);
    private ArrayList<Coordinates> coords = new ArrayList<>();

    public FitnessFunction(double[] a0, double[][] B, double[] c){
        for(int i = 0; i < a0.length; i++)
            this.a0[i] = a0[i];
        for(int i = 0; i < B.length; i++)
            for(int j = 0; j < B[i].length; j++)
                this.B[i][j] = B[i][j];
        for(int i = 0; i < c.length; i++)
            this.c[i] = c[i];
        this.v[0] = 0;
        this.v[1] = 0;
        coords.add(currentCoords);
    };

    private void makeStep(double dt){
        double a_r = a0[0] + B[0][0]*v[0] + B[0][1]*v[1] + c[0]*currentCoords.r;
        System.out.println("a_r="+a_r);
        double dv_r = a_r * dt;
        v[0] += dv_r;
        double dr = v[0] * dt;
        currentCoords.r += dr;
        double a_phi = a0[1] + B[1][0]*v[0] + B[1][1]*v[1] + c[1]*currentCoords.r;
        double dv_phi = a_phi * dt;
        v[1] += dv_phi;
        double dphi = v[1] * dt;
        currentCoords.phi += dphi;
    }

    public void testParameters() {
        double dt = 0.01;
        for (double i = 0.0; i < 2.0; i += dt) {
            makeStep(dt);
            coords.add(currentCoords);
            System.out.println( "v[0]=" + v[0] + "\n" +
                                "v[1]=" + v[1] + "\n" +
                                "r=" + currentCoords.r + "\n" +
                                "phi=" + currentCoords.phi + "\n");
        }
    }

    public ArrayList<Coordinates> getCoords(){
        return coords;
    }
}

