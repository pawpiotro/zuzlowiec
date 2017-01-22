package AlgorithmService;

import java.lang.*;

public abstract class AlgoEvo implements Runnable
{
    // volatile - zeby nie odkladac operacji na niej w pamieci w innej kolejnosci niz wskazana w kodzie zrodlowym
    static double fxr = 0.0; // zmienna wspoldzielona
    protected Object lock = new Object();
    protected int size;   // ilosc parametrow
    protected double params[]; // parametry znajdowane przez algorytm
    static protected double best_params[];

    abstract protected double fitness(double fparams[], int fsize);

    public AlgoEvo(int csize)
    {
        size = csize;
        params = new double[size];
        for (int i = 0; i < csize; i++){
            params[i] = Math.random();
        }
    }

    protected double BoxMullerTransform(){
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
        while (sigma > 0.5) {
            // wygeneruj potomka y
            //double max = Double.MIN_VALUE;
            //double min = Double.MAX_VALUE;
            //double temp[] = new double[size];
            for (int i = 0; i < size; i++)
            {
                double temp = params[i] + sigma * BoxMullerTransform();
                if (temp > 1.0)
                    temp = 1.0;
                if (temp < -1.0)
                    temp = -1.0;
                yparams[i] = temp;
            }
            /*for (int i = 0; i < size; i++) {
                temp[i] = params[i] + sigma * BoxMullerTransform();
                if(temp[i] > max)
                    max = temp[i];
                else if (temp[i] < min)
                    min = temp[i];
            }

            for (int i = 0; i < size; i++)
            {
                if(temp[i] < 0)
                    yparams[i] = ;
            }*/

            // przypisz nowe parametry
            if (fitness(yparams, size) > fitness(params, size))
            {
                for (int i = 0; i < size; i++)
                    params[i] = yparams[i];
                phi += 0.1;

                // porownaj wartosc miedzy watkami
                synchronized(lock)
                {
                    double tmp = fitness(params, size);
                    if (tmp > fxr)
                    {
                        setfxr(tmp);
                        for (int i = 0; i < size; i++)
                            best_params[i] = params[i];
                        System.out.println("fxr: " + fxr + "\n");
                    }
                    System.out.println(Thread.currentThread().getId() + " " + tmp + "\n");
                }
            }
            // co 10 krok zmien sigma
            if (m++ == 10) {
                if (phi < 0.2){
                    sigma = c1 * sigma;}
                else if (phi > 0.2)
                    sigma = c2 * sigma;
                m = 0;
                phi = 0.0;
            }

            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException ex)
            {

            }
        }
    }

    public static double getfxr() {
        return fxr;
    }

    public static double[] getBestParams() { return best_params; }

    protected void setfxr(double function)
    {
        fxr = function;
    }

}

