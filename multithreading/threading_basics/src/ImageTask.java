import java.util.concurrent.Callable;

public class ImageTask implements Callable<String> {
    private final String imageName;

    public ImageTask(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String call() throws Exception {
        System.out.println( "Processing image: " + this.imageName + " by :" + Thread.currentThread().getName());
        Thread.sleep(3000);
        return "Done with " + imageName;
    }
}
