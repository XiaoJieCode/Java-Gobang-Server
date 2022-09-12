package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketCommend {
    public static final String connect = "connect";

    private SocketCommend(){}

    public static void sendCommend(Socket socket,String[] cmd) throws IOException {
        String type = cmd[0];
        switch (type){
            case connect:
                connect(socket, cmd);
        }

    }
    public static void connect(Socket socket,String[] pars) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(pars[1].getBytes(StandardCharsets.UTF_8));
    }
}
