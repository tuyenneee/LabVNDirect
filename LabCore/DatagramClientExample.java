import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class DatagramClientExample {
    public static byte[] bytes = new byte[4096];
    static DatagramPacket packet;
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        bytes = "Client hand up say hello".getBytes();
        packet = new DatagramPacket(bytes, bytes.length, address, 4445);
        socket.send(packet);

        packet = new DatagramPacket(bytes, bytes.length);
        socket.receive(packet);

        System.out.println("From server: "+new String(packet.getData(), 0, packet.getLength()));
        socket.close();
    }
}
