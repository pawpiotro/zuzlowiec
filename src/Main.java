import java.awt.*;
import java.lang.*;
import AlgorithmService.MultiThreadAlgoEvo;
import ResultService.*;


public class Main
{
    public static void main(String [] args) {
        /*
        MultiThreadAlgoEvo multiThreadAlgoEvo = new MultiThreadAlgoEvo(); // Pytanie - jak robimy z liczba watkow
        while(!multiThreadAlgoEvo.getIsSuccess())
        {
            multiThreadAlgoEvo.prepareNewThread();
            multiThreadAlgoEvo.startAlgo();
        }
        */
        double[] a0 = {0.5, -1.0};
        double[][] B = {{1.2, 0.6}, {0.7, 1.1}};
        double[] c = {0.3, 0.2};
        FitnessFunction function = new FitnessFunction(a0, B, c);
        function.testParameters();
        function.printarray();
        /*EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {*/
        Draw tmp = new Draw(function.getCoords());
        tmp.init();
    }

}
