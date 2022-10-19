import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServerExample implements Runnable {
    public static byte[] bytes = new byte[4096];
        DatagramSocket socket;
    public DatagramServerExample() throws Exception {
        socket = new DatagramSocket(4445);
        System.out.println("Data server listening...");
    }

    @Override
    public void run() {
        try {
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            System.out.println("From client: "+new String(packet.getData(), 0 , packet.getLength()));
            bytes = "Server say hello".getBytes();
            socket.send(new DatagramPacket(bytes, bytes.length, packet.getAddress(), packet.getPort()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new DatagramServerExample()).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
