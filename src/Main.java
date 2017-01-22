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

        MultiThreadAlgoEvo multiThreadAlgoEvo = new MultiThreadAlgoEvo(); // Pytanie - jak robimy z liczba watkow
        multiThreadAlgoEvo.repeatAlgo();
        //System.out.println("fxr: " + AlgoEvo.getfxr() + "\n Parametry:");
        double wynik[] = AlgoEvo.getBestParams();
        //for (int i =0; i < 8; i++)
            //System.out.println(wynik[i]);

        double[] a0 = {wynik[0], wynik[1]};
        double[][] B = {{wynik[2], wynik[3]}, {wynik[4], wynik[5]}};
        double[] c = {wynik[6], wynik[7]};
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