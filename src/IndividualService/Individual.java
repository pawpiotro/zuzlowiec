package IndividualService;

/**
 * Created by Pawel on 21-Jan-17.
 */
public class Individual {
    private double[] a0 = new double[2];
    private double[][] B = new double[2][2];
    private double[] c = new double[2];


    public Individual(double[] a0, double[][] B, double[] c){
        for(int i = 0; i < a0.length; i++)
            this.a0[i] = a0[i];
        for(int i = 0; i < B.length; i++)
            for(int j = 0; j < B[i].length; j++)
                this.B[i][j] = B[i][j];
        for(int i = 0; i < c.length; i++)
            this.c[i] = c[i];
    }

    public double[] getA0() {
        return a0;
    }

    public double[][] getB() {
        return B;
    }

    public double[] getC() {
        return c;
    }

}
