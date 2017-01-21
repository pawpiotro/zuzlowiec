package AlgorithmService;

import java.lang.*;

public class AlgoEvo implements Runnable
{
    // volatile - zeby nie odkladac operacji na niej w pamieci w innej kolejnosci niz wskazana w kodzie zrodlowym
    volatile static int function; // zmienna wspoldzielona

    public AlgoEvo()
    {
    }

    private double BoxMullerTransform(){
        double x = Math.random();
        double y = Math.random();
        return x * Math.sqrt(-2.0 * Math.log(x))*Math.cos(2*Math.PI*y);
    }

    public void run()
    {
        try
        {
            System.out.println("Watek " + Thread.currentThread().getId() + " wypisuje " + function);
            ++function;
            Thread.sleep(10);
        }
        catch(InterruptedException e)
        {

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

