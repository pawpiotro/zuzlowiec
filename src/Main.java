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
        double[] a0 = {1.0, 2.0};
        double[][] B = {{3.0, 4.0}, {5.0, 6.0}};
        double[] c = {3.0, 5.0};
        FitnessFunction function = new FitnessFunction(a0, B, c);
        function.testParameters();

        /*EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {*/
        Draw tmp = new Draw(function.getCoords());
        tmp.init();
    }

}
