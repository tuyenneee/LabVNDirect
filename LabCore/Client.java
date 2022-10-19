import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client{
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 8000);
        Hello stub = (Hello) registry.lookup("Hello1");
        System.out.println("Response: "+stub.say("Tuyen"));
    }
}
