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

    public synchronized void printInfo(){
        System.out.println( "a0: " + a0[0] + "   " + a0[1] + "\n" +
                            "B:  " + B[0][0] + "   " + B[0][1] + "\n" +
                            "    " + B[1][0] + "   " + B[1][1] + "\n" +
                            "c:  " + c[0] + "   " + c[1]);
    }

}
