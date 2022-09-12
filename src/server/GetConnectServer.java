package server;

import log.Log;
import objects.Client;
import serverUI.ServerUI;

import javax.swing.*;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GetConnectServer extends Thread implements Closeable, AutoCloseable {
    ServerSocket serverSocket = null;
    Boolean isRun = null;

    @Override
    public void start() {
        isRun = true;
        super.start();

    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(7103);
            while (isRun) {
                Socket socket = serverSocket.accept();
                ClientConnectServer.connect(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(ServerUI.serverFrame, "服务器接收错误");
        }
    }

    @Override
    public void close() throws IOException {
        if ( isRun!=null){
        isRun = false;
        }

        this.interrupt();
    }
}
