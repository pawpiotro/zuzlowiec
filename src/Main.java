import java.lang.*;
import AlgorithmService.MultiThreadAlgoEvo;

public class Main
{
    public static void main(String [] args)
    {
        MultiThreadAlgoEvo multiThreadAlgoEvo = new MultiThreadAlgoEvo();
        while(!multiThreadAlgoEvo.getIsSuccess())
        {
            multiThreadAlgoEvo.prepareNewThread();
            multiThreadAlgoEvo.startAlgo();
        }
    }

}
