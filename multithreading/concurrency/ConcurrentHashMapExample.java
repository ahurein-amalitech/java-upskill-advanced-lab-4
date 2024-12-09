import java.util.concurrent.*;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();

        String[] words = {"apple", "banana", "apple", "orange", "banana", "banana", "apple"};

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (String word : words) {
            executor.submit(() -> {
                wordCount.merge(word, 1, Integer::sum);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        wordCount.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
