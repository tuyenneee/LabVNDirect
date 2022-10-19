import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


class CallableSample implements Callable<Integer> {
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public Integer call() throws Exception {
        lock.lock();
        Thread current = Thread.currentThread();
        AtomicInteger total = new AtomicInteger(0);
        IntStream.range(0, 10).forEach(number -> {
            System.out.println(current.getName() + " -Object " + this + " is running " + total.addAndGet(number));
            Random random = new Random();
            LongStream longStream = random.longs(100, 1000);
            long sleep = longStream.findFirst().getAsLong();
        });
        lock.unlock();
        return total.get();
    }
}

class FeatureExampleTest {
    public static void main(String[] args) {
        CallableSample callableSample = new CallableSample();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future future = executor.submit(callableSample);
        System.out.println("Future Done ? " + future.isDone());
        Object result = 0;
        try {
            result = future.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Future Done ? " + future.isDone() + " -Result = " + result);
    }
}

class LockExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool(4);
        //CallableSample sample = new CallableSample();
        List callables = Arrays.asList(
                new CallableSample(), new CallableSample(),
                new CallableSample(), new CallableSample()
        );
        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ReadWriteLockExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Map<String, String> map = new HashMap<String, String>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        executor.submit(() -> {
            System.out.println("Start writing");
            lock.writeLock().lock();
            try {
                TimeUnit.SECONDS.sleep(10);
                map.put("foo", "bar");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
                System.out.println("End writing");
            }
        });
        Runnable readTask = () -> {
            System.out.println("Start reading");
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
            } finally {
                lock.readLock().unlock();
                System.out.println("End reading");
            }
        };
        executor.submit(readTask);
    }
}


