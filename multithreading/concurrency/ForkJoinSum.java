import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinSum extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;
    private long[] arr;
    private int start;
    private int end;

    public ForkJoinSum(long[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            ForkJoinSum leftTask = new ForkJoinSum(arr, start, mid);
            ForkJoinSum rightTask = new ForkJoinSum(arr, mid, end);

            leftTask.fork();
            rightTask.fork();

            long leftResult = leftTask.join();
            long rightResult = rightTask.join();

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        long[] arr = new long[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSum task = new ForkJoinSum(arr, 0, arr.length);

        long sum = pool.invoke(task);
        System.out.println("Sum: " + sum);
    }
}
