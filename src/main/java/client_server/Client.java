package client_server;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket()) {
            SocketAddress address= new InetSocketAddress("localhost", 2021);
            socket.connect(address);

            byte[] bytes=new byte[5];
            socket.getOutputStream().write("123456789101112".getBytes(StandardCharsets.UTF_8));


            socket.getInputStream().read(bytes);
            System.out.println("client msg is "+new String(bytes));
            System.out.println(socket.isConnected());
            System.out.println(socket.isBound());
            System.out.println(socket.isInputShutdown());
            System.out.println(socket.isOutputShutdown());
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
