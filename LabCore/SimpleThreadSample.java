import java.util.Arrays;

import static java.util.Arrays.stream;

public class SimpleThreadSample {
    public static void main(String[] args) {
        new Thread(() -> {
            stream(args).forEach(ele -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(ele);
            });
        }).start();
    }
}
