package objects;

import log.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

public class Client {

    public static final ArrayList<Client> clients = new ArrayList<>();
    String clientName = null;
    InetAddress clientIp = null;
    Socket clientSocket = null;


    static {
        Timer timer = new Timer("检查客户端是否在线",true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clients.removeIf(client -> {
                    boolean flag = false;
                    try {
                        client.clientSocket.getInputStream().read();

                    } catch (IOException e) {
                        Log.log("客户端"+client.clientName+"掉线");
                        flag = true;
                    }
                    return flag;
                });
            }
        },0,3000);

    }

    public Client(Socket clientSocket, String clientName) {
        this.clientName = clientName;
        this.clientIp = clientSocket.getInetAddress();
        this.clientSocket = clientSocket;
    }

    @Override
    public String toString() {
        return clientName;
    }

    public String getClientName() {
        return clientName;
    }
}
