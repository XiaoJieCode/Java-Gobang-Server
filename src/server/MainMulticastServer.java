package server;

import log.Log;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class MainMulticastServer implements Closeable, AutoCloseable {
    Integer serverPort = null;
    InetAddress serverIp = null;
    InetAddress serverGroup = null;
    MulticastSocket multicastSocket = null;
    DatagramPacket buffer = null;
    String data = null;
    Timer timer = null;
    GetConnectServer getConnectServer = null;

    public MainMulticastServer(InetAddress serverGroup, InetAddress serverIp, Integer serverPort){
        this.serverPort = serverPort;
        this.serverIp = serverIp;
        this.serverGroup = serverGroup;


    }

    public MainMulticastServer() throws UnknownHostException {
        this.serverPort = 7104;
        this.serverIp = InetAddress.getLocalHost();
        this.serverGroup = InetAddress.getByName("224.224.224.224");
    }


    public void start() throws IOException {
        Log.log("\n------------服务器启动------------");
        multicastSocket = new MulticastSocket(serverPort);
        data = "serverPort="+ serverPort +"\nserverIp="+serverIp.getHostAddress()+"\n";
        buffer = new DatagramPacket(
                data.getBytes(StandardCharsets.UTF_8),
                data.getBytes(StandardCharsets.UTF_8).length,
                serverGroup,
                serverPort
        );

        timer = new Timer("MulticastServerThread",true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    multicastSocket.send(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 2, 1000);

        GetConnectServer getConnectServer = new GetConnectServer();
        getConnectServer.start();
    }

    @Override
    public void close() throws IOException {

        Log.log("\n------------服务器关闭------------");
        if (timer!=null){
            timer.cancel();
        }

        if (getConnectServer!=null){
            getConnectServer.close();
        }
    }
}
