package ResultService;

import AlgorithmService.AlgoEvo;
import IndividualService.Individual;
import ResultService.FitnessFunction;

/**
 * Created by Dominik on 2017-01-21.
 */
public class AlgoEvoZuzlowiec extends AlgoEvo
{
    public AlgoEvoZuzlowiec()
    {
        super(8);
    }

    static
    {
        best_params = new double[8];
    }

    protected double fitness(double fparams[], int fsize)
    {
        double[] a0 = {fparams[0], fparams[1]};
        double[][] B = {{fparams[2], fparams[3]}, {fparams[4], fparams[5]}};
        double[] c = {fparams[6], fparams[7]};
        Individual x = new Individual(a0, B, c);
        FitnessFunction function = new FitnessFunction(x, 600);
        return function.testParameters();
    }

    protected void setfxr(double function)
    {
        // <----- TUTAJ RYSOWANIE CZY COS
        super.setfxr(function);
    }
}
