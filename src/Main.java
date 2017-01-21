import java.awt.*;
import java.lang.*;
import AlgorithmService.MultiThreadAlgoEvo;
import ResultService.*;
import IndividualService.Individual;


public class Main
{
    public static void main(String [] args) {
        int size = 600;

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
        Individual x = new Individual(a0, B, c);
        FitnessFunction function = new FitnessFunction(x, size);
        function.testParameters();
        function.printarray();
        /*EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {*/
        Draw tmp = new Draw(function.getCoords(), size);
        tmp.init();
    }

}