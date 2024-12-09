import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TheadPoolApp {
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        String[] imageNames = {"image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg", "image5.jpg"};

        CompletableFuture<String> image1Task = CompletableFuture.supplyAsync(() ->{
            try {
                System.out.println("Processing image 1");
                Thread.sleep(300);
                return "result from image one";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> image2Task = image1Task.thenApply((String result) -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " task1 image1result: "+ result);
            return "result from image two using image one";
        });

        System.out.println("Final results: " + image2Task.get());



        List<Future<String>> futures = new ArrayList<>();
        for (String image : imageNames) {
            ImageTask task = new ImageTask(image);
            Future<String> result = executorService.submit(task);
            futures.add(result);
        }

        Callable<String> dependsOnOne = () -> {
            Future<String> processOne = futures.getFirst();
            System.out.println("First image task: " + processOne.get());
            Thread.sleep(200);
            return "Task completed depending on image task one";
        };

        Future<String> result = executorService.submit(dependsOnOne);
        System.out.println(result.get());

//        for (String image : imageNames) {
//            ImageTask task = new ImageTask(image);
//            Future<String> result = executorService.submit(task);
//            // blocking with future.get
//            System.out.println(result.get());
//        }

//
//        for (Future<String> future : futures) {
//            try {
//                System.out.println(future.get());
//            } catch (ExecutionException e) {
//                System.err.println("Task execution failed: " + e.getMessage());
//            }
//        }

//        executorService.shutdown();

    }
}
