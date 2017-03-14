package tools.logic;

public class Result {

    private double[] result;

    private long iterations;

    private long time;

    private boolean success;

    public Result(boolean success, double[] result, long iterations, long time) {
        this.result = result;
        this.iterations = iterations;
        this.time = time;
        this.success = success;
    }

    public double[] getResult() {
        return result;
    }

    public long getIterations() {
        return iterations;
    }

    public long getTime() {
        return time;
    }

    public boolean isSuccess() {
        return success;
    }
}
