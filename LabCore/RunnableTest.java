public class RunnableTest {
    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " say hello!");
//            }
//        };
//        new Thread(runnable).start();

//        Runnable runnable = () -> {
//            System.out.println(Thread.currentThread().getName()+" hello");
//        };
//        new Thread(runnable).start();

//        new Thread(() -> System.out.println
//                (Thread.currentThread().getName()+" hello!")).start();

        new Thread(() -> {
            System.out.println
                    (Thread.currentThread().getName() + " hello!");
            System.out.println("1 + 1 = " + (1 + 1));
        }).start();
    }
}
