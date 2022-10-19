import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SynchronizedExample {
    static void addData(List<Integer> list) {
        IntStream.range(0, 1000).forEach(index -> {
            try {
                list.add(index);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        });
    }

    public static void main(String[] args) {
        List<List<Integer>> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            new Thread(() -> addData(list)).start();
            new Thread(() -> addData(list)).start();
            values.add(list);
            values.forEach(list1 -> System.out.println("Number items of list -->" + list.size()));
        }

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
