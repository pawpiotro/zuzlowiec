import AlgorithmService.AlgoEvo;
import AlgorithmService.MultiThreadAlgoEvo;
import IndividualService.Individual;
import ResultService.Draw;
import ResultService.FitnessFunction;

public class Main {
    public static void main(String[] args) {

        int size = 600;
        Zuzlowiec textArea = new Zuzlowiec();

        MultiThreadAlgoEvo multiThreadAlgoEvo = new MultiThreadAlgoEvo();
        multiThreadAlgoEvo.repeatAlgo();

        Draw tmp = new Draw(size);
        tmp.init();

        System.out.println("======================");
        double wynik[] = AlgoEvo.getBestParams();
        for (int i = 0; i < 8; i++)
            System.out.println(wynik[i] + " ");

        double[] a0 = {wynik[0], wynik[1]};
        double[][] B = {{wynik[2], wynik[3]}, {wynik[4], wynik[5]}};
        double[] c = {wynik[6], wynik[7]};
        Individual x = new Individual(a0, B, c);
        FitnessFunction function = new FitnessFunction(x);
        function.testParameters();
        //function.printarray();
        Draw best = new Draw(function.getCoords(), size);
        best.init();
    }

}