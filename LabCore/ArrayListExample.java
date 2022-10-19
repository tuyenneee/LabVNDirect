
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class ArrayListExample {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, args);
        IntStream.range(0, list.size()).forEach(i -> {
            System.out.println("Element at " + i + " is " + list.get(i));
        });
    }
}
