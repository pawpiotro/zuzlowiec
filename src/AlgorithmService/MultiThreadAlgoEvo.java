package AlgorithmService;

public class MultiThreadAlgoEvo
{
    private int threadsNumber;
    private boolean isSuccess;

    public MultiThreadAlgoEvo(int threadsNumber)
    {
        this.threadsNumber = threadsNumber;
    }

    public boolean getIsSuccess()
    {
        return isSuccess;
    }

    public void incThreadNumber()
    {
        ++threadsNumber;
    }
}
