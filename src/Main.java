import java.awt.*;
import java.lang.*;

import AlgorithmService.AlgoEvo;
import AlgorithmService.MultiThreadAlgoEvo;
import ResultService.*;
import IndividualService.Individual;


public class Main
{
    public static void main(String [] args) {
        int size = 600;

        /*MultiThreadAlgoEvo multiThreadAlgoEvo = new MultiThreadAlgoEvo(); // Pytanie - jak robimy z liczba watkow
        multiThreadAlgoEvo.repeatAlgo();
        System.out.println("fxr: " + AlgoEvo.getfxr() + "\n Parametry:");
        double wynik[] = AlgoEvo.getBestParams();
        for (int i =0; i < 8; i++)
            System.out.println(wynik[i]);*/

        double[] a0 = {-0.6650, -0.221507};
        double[][] B = {{-0.5621, 0.02734}, {1.0, 0.99120}};
        double[] c = {0.069207, -0.4319};
        Individual x = new Individual(a0, B, c);
        FitnessFunction function = new FitnessFunction(x, size);
        System.out.println("Fitness function returned value: "+function.testParameters());
        function.printarray();

        //EventQueue.invokeLater(new Runnable() {
            //@Override
            //public void run() {
        Draw tmp = new Draw(function.getCoords(), size);
        tmp.init();
    }

}