import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Creating users");
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            users.add(new User());
        }

        System.out.println("Done creating users");
    }
}
