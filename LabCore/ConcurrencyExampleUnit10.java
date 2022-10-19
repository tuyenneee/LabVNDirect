import java.util.ArrayList;
import java.util.List;

public class ConcurrencyExampleUnit10 {
    static void addData(List list){
        while (true){
            int random = (int)(Math.random()*100);
            list.add(random);
            System.out.println(Thread.currentThread().getName()+ " Add new data "+random);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(random == 90) break;
        }
    }

    static void printData(List list){
        while (true){
            try {
                System.out.println("==================");
                list.forEach(value -> System.out.println(Thread.currentThread().getName()+" Value = "+value));
                try {
                    Thread.sleep(999);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        new Thread(() -> addData(list)).start();

        new Thread(() -> printData(list)).start();
    }
}
