package tools.logic;

public class Result {

    private double[] result;

    private long iterations;

    private long time;

    private boolean success;

    private String desc;

    public Result(double[][] matrix, boolean success, double[] result, long iterations, long time, String description) {
        this.result = result;
        this.iterations = iterations;
        this.time = time;
        this.success = success;
        this.desc = description;
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

    public String getDesc() {
        return desc;
    }
}
