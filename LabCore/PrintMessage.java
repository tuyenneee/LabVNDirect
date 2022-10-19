import java.io.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.stream;

public class PrintMessage implements Runnable, Serializable {
    public PrintMessage(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public synchronized void run() {
        String[] elements = message.split(" ");
        stream(elements).forEach(ele -> {
            System.out.println(Thread.currentThread().getName()
                    + " Print " + ele);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            try {
                Thread.sleep((int)(Math.random()*3)*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

class ThreadTest {
    public static void main(String[] args) {
        PrintMessage message = new PrintMessage("Hello goodbye thank you!");
        new Thread(message).start();
        new Thread(message).start();
    }
}
class ObjectWriterExample{
    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Temp");
        ObjectOutputStream output = null;
        FileOutputStream fileOutput =
                new FileOutputStream(new File(folder, "my_object"));
        output = new ObjectOutputStream(fileOutput);
        PrintMessage printMessage;
        output.writeObject(new PrintMessage("alo alo alo"));
    }
}

class ObjectReaderExample {
    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Temp");
        ObjectInputStream input = null;
        input = new ObjectInputStream(
                new FileInputStream(new File(folder, "my_object")));
        Object obj = input.readObject();
        Method method = obj.getClass().getMethod("run", null);
        method.invoke(obj, null);

        input.close();
    }
}
