package AlgorithmService;

import java.lang.*;

public class AlgoEvo implements Runnable
{
    public AlgoEvo()
    {
    }

    public void run()
    {
        try
        {
            System.out.println("Watek " + Thread.currentThread().getId() + " pozdrawia");
            Thread.sleep(10);
        }
        catch(InterruptedException e)
        {

        }
    }

}

