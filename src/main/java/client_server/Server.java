package client_server;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private static final int PORT=2021;
    public static void main(String[] args) {
        Server server=new Server();
        try (
                ServerSocket socket = new ServerSocket(PORT,2);
        ) {
            System.out.println("listening port is "+PORT);
            while (true){
                Socket client = socket.accept();
                byte[] bytes=new byte[64];
                int read_length = client.getInputStream().read(bytes);
                if (read_length!=-1){
                    String msg = new String(bytes);
//                    System.out.println("msg "+msg);
                    String url=msg.substring(msg.indexOf("GET ")+5,msg.indexOf("HTTP")-1);
                    System.out.println("url is "+url);
                    switch (url){
                        case "up":
                            System.out.println("get a up");
                            client.getOutputStream().write(server.getResponseMsg("up success!").getBytes());
                            break;
                        case "down":
                            System.out.println("get a down");
                            client.getOutputStream().write(server.getResponseMsg("down success!").getBytes());
                            break;
                        default:
                            System.out.println("requested url '"+url+"' is not supported!");
                            client.getOutputStream().write(server.getResponseMsg("requested url '"+url+"' is not supported!").getBytes());
                            break;
                    }
                }else {
                    System.out.println(read_length);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getResponseMsg(String msg){
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=utf-8\r\n" +
                "Connection: keep-alive\r\n" +
                "Content-length: "+msg.getBytes().length +"\r\n"+
                "\r\n"+
                msg+
                "\r\n";
    }
}
