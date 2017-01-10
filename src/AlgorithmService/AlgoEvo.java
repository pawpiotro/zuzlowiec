package AlgorithmService;

import java.lang.*;

public class AlgoEvo implements Runnable
{
    // volatile - zeby nie odkladac operacji na niej w pamieci w innej kolejnosci niz wskazana w kodzie zrodlowym
    volatile static int function; // zmienna wspoldzielona

    public AlgoEvo()
    {
    }

    public synchronized void run()
    {
        try
        {
            ++function;
            System.out.println("Watek " + Thread.currentThread().getId() + " wypisuje " + function);
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

