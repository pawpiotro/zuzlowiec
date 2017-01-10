import java.lang.*;
import AlgorithmService.MultiThreadAlgoEvo;

public class Main
{
    public static void main(String [] args)
    {
        int threadsNumber = 2;
        MultiThreadAlgoEvo multiThreadAlgoEvo = new MultiThreadAlgoEvo(threadsNumber); // Pytanie - jak robimy z liczba watkow
        while(!multiThreadAlgoEvo.getIsSuccess())
        {

            multiThreadAlgoEvo.incThreadNumber();
        }
    }

}
