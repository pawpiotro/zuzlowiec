package ResultService;

import AlgorithmService.AlgoEvo;
import IndividualService.Individual;

/**
 * Created by Dominik on 2017-01-21.
 */
public class AlgoEvoZuzlowiec extends AlgoEvo
{

    double fitness(double fparams[], int fsize)
    {
        double[] a0 = {this.params[0], super.params[1]};
        double[][] B = {{params[2], params[3]}, {params[4], params[5]}};
        double[] c = {params[6], params[7]};
        Individual x = new Individual(a0, B, c);
        FitnessFunction function = new FitnessFunction(x, 600);
        return function.testParameters();
    }
}
