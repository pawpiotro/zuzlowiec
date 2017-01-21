package AlgorithmService;

import java.lang.*;

public abstract class AlgoEvo implements Runnable
{
    // volatile - zeby nie odkladac operacji na niej w pamieci w innej kolejnosci niz wskazana w kodzie zrodlowym
    static int function; // zmienna wspoldzielona
    private Object lock = new Object();
    protected int size;   // ilosc parametrow
    protected double params[] = new double[size]; // parametry znajdowane przez algorytm

    abstract double fitness(double fparams[], int fsize);

    public AlgoEvo(int csize)
    {
        size = csize;
        for (int i = 0; i < csize; i++){
            params[i] = Math.random();
        }
    }

    private double BoxMullerTransform(){
        double x = Math.random();
        double y = Math.random();
        return x * Math.sqrt(-2.0 * Math.log(x))*Math.cos(2*Math.PI*y);
    }

    public void run()
    {
        /*try
        {
            synchronized (lock)
            {
                System.out.println("Watek " + Thread.currentThread().getId() + " wypisuje " + function);
                ++function;
            }
            Thread.sleep(10);
        }
        catch(InterruptedException e)
        {

        }*/

        double yparams[] = new double[size];
        double sigma = 1.0;
        double phi = 0.0;
        double c1 = 0.82, c2 = 1.2;
        int m = 0;
        while (sigma > 0.1) {
            // wygeneruj potomka y
            for (int i = 0; i < size; i++) {
                yparams[i] = params[i] + sigma * BoxMullerTransform();
            }
            // przypisz nowe parametry
            if (fitness(yparams, size) > fitness(params, size)) {
                for (int i = 0; i < size; i++)
                    params[i] = yparams[i];
                phi += 0.1;
            }
            // co 10 krok zmien sigma
            if (m++ == 10) {
                m = 0;
                if (phi < 0.2)
                    sigma = c1 * sigma;
                else if (phi > 0.2)
                    sigma = c2 * sigma;
            }
        }
    }

    public static int getFunction() {
        return function;
    }

    public static void setFunction(int function)
    {
        AlgoEvo.function = function;
    }

}

