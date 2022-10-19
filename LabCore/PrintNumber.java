import java.util.concurrent.TimeUnit;

class PrintNumber implements Runnable {
    //    private int number = 1;
//    private boolean alive = true;
//
//    public int getNumber(){
//        return number;
//    }
//    public void setAlive(boolean alive) {
//        this.alive = alive;
//    }
//    @Override
//    public synchronized void run() {
//        Thread current = Thread.currentThread();
//        while (number<10){
//            number++;
//            System.out.println(current.getName()+" Number is \""+number+"\"");
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println(current.getName()+" is stopped!");
//    }
//}
//    class ThreadNumberTest{
//    public static void main(String[] args) {
//        PrintNumber number = new PrintNumber();
//        Thread thread = new Thread(number);
//        Thread thread1 = new Thread(number);
//        thread.setName("Hanoi_t1");
//        thread1.setName("Bacninh_Thread");
//        thread.setDaemon(true);
//        thread.start();
//        thread.isDaemon();
////        try {
////            thread.join();
////        } catch (Exception e) {
////            System.out.println(e);
////        }
//        //thread1.start();
//        System.out.println("abc");
////        while(thread.isAlive()){
////            if(number.getNumber()%10==0) number.setAlive(false);
////            try {
////                TimeUnit.SECONDS.sleep(1);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
////        }
//    }
    Integer number = 1;


    @Override
    public void run() {
        Thread current = Thread.currentThread();

            while (true) {
                number++;
                System.out.println(current.getName() + " Number is \"" + number + "\"");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(number%10==0) break;
            }

        System.out.println(current.getName() + " is stopped!");
    }

    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();
        Thread thread = new Thread(number);
        thread.setName("FS_Thread 1 ");
        thread.start();
        Thread thread1 = new Thread(number);
        thread1.setName("FS_Thread 2 ");
        thread1.start();
    }
}
