import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();
        List callables = Arrays.asList(
                new CallableSample(), new CallableSample()
        );
        Stream<Integer> resultStream;
        try {
            Stream<Future<Integer>> stream = executor.invokeAll(callables).stream();
            resultStream = stream.map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Object[] results = resultStream.toArray(Integer[]::new);
        stream(results).forEach(System.out::println);
    }
}
