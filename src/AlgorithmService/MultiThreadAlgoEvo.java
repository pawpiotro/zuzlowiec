package AlgorithmService;

import java.util.ArrayList;

public class MultiThreadAlgoEvo implements AlgorithmInterface
{
    private ArrayList<Thread> algoEvoList = new ArrayList<>();
    private boolean isSuccess = false;

    public MultiThreadAlgoEvo()
    {
    }

    public void repeatAlgo()
    {
        while(!isSuccess)
        {
            prepareNewThread();
            startAlgo();
        }
    }

    public void startAlgo()
    {
        AlgoEvo.setFunction(0);
        for (Thread algoEvoThread: algoEvoList)
        {
            algoEvoThread.start();
        }
        try
        {
            for (Thread algoEvoThread: algoEvoList)
            {
                algoEvoThread.join();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if(algoEvoList.size() == 5) // na razie zeby program sie konczyl
            isSuccess = true;
    }

    public void prepareNewThread()
    {
        Runnable algoEvo = new AlgoEvo();
        Thread algoEvoThread = new Thread(algoEvo);
        algoEvoList.add(algoEvoThread);
    }

    /*boolean getIsSuccess()
    {
        return isSuccess;
    }*/

}
