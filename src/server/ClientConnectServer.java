package server;

import log.Log;
import objects.Client;
import utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.IllegalFormatCodePointException;

public class ClientConnectServer {


    public static void connect(Socket socket) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                InputStream inputStream = null;
                String clientName = null;

                try {
                    inputStream = socket.getInputStream();
                    clientName = IOUtils.readAll(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 检查是否重复客户端, 以客户端名字为唯一ID
                boolean flag = false;
                for (Client client : Client.clients) {
                    if (client.getClientName().equals(clientName)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    Log.log("客户端重复！");
                } else {
                    Client client = new Client(socket, clientName);
                    Client.clients.add(client);
                    Log.log("客户端连接成功：" + client + "");
                    Log.log("当前客户端列表：" + Client.clients);
                }
            }
        };
        thread.start();

    }
}
