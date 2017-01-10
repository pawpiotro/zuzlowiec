package AlgorithmService;

import java.util.ArrayList;

public class MultiThreadAlgoEvo
{
    private int threadsNumber;
    private ArrayList<Runnable> algoEvoList = new ArrayList<>();
    private boolean isSuccess = false;


    public MultiThreadAlgoEvo(int threadsNumber)
    {
        this.threadsNumber = threadsNumber;
    }

    public void startAlgo()
    {
        for (Runnable algoEvo: algoEvoList)
        {
            Thread algoEvoThread = new Thread(algoEvo);
            algoEvoThread.start();
        }
        if(algoEvoList.size() == 5)
            isSuccess = true;
    }

    public void prepareNewThread()
    {
        Runnable algoEvo = new AlgoEvo();
        algoEvoList.add(algoEvo);
        ++threadsNumber;
    }

    public boolean getIsSuccess()
    {
        return isSuccess;
    }

}
