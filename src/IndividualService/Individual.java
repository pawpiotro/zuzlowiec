package IndividualService;

public class Individual {
    private double[] a0 = new double[2];
    private double[][] B = new double[2][2];
    private double[] c = new double[2];


    public Individual(double[] a0, double[][] B, double[] c) {
        System.arraycopy(a0, 0, this.a0, 0, a0.length);
        for (int i = 0; i < B.length; i++)
            System.arraycopy(B[i], 0, this.B[i], 0, B[i].length);
        System.arraycopy(c, 0, this.c, 0, c.length);
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
