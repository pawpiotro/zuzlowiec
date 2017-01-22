package AlgorithmService;

import ResultService.AlgoEvoZuzlowiec;

import java.util.ArrayList;

public class MultiThreadAlgoEvo  implements AlgorithmInterface
{
    private ArrayList<Thread> algoEvoList = new ArrayList<>();
    private boolean isSuccess = false;
    private static int threadsNumber = 1;

    public MultiThreadAlgoEvo()
    {
    }

    public void repeatAlgo()
    {
        while(!isSuccess)
            startAlgo();
    }

    public void startAlgo()
    {
        //AlgoEvo.setFunction(0);
        System.out.println("TUTAJ");
        for (int i = 0; i < threadsNumber; ++i)
        {
            Thread algoEvoThread = new Thread(new AlgoEvoZuzlowiec());
            algoEvoList.add(algoEvoThread);
            algoEvoThread.start();
        }
        try
        {
            for (Thread algoEvoThread: algoEvoList)
                algoEvoThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if(AlgoEvo.getfxr() != 0.0) // na razie zeby program sie konczyl
            isSuccess = true;
        algoEvoList.clear();
    }

    /*boolean getIsSuccess()
    {
        return isSuccess;
    }*/

}
