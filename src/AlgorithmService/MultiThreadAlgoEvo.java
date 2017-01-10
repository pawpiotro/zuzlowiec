package AlgorithmService;

import java.util.ArrayList;

public class MultiThreadAlgoEvo
{
    private ArrayList<Runnable> algoEvoList = new ArrayList<>();
    private boolean isSuccess = false;

    public MultiThreadAlgoEvo()
    {
    }

    public void startAlgo()
    {
        AlgoEvo.setFunction(0);
        for (Runnable algoEvo: algoEvoList)
        {
            Thread algoEvoThread = new Thread(algoEvo);
            algoEvoThread.start();
        }
        /*try
        {
            Thread.currentThread().join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
        if(algoEvoList.size() == 5) // na razie zeby program sie konczyl
            isSuccess = true;
    }

    public void prepareNewThread()
    {
        Runnable algoEvo = new AlgoEvo();
        algoEvoList.add(algoEvo);
    }

    public boolean getIsSuccess()
    {
        return isSuccess;
    }

}
