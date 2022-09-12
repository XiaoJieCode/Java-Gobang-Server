package serverUI;

import server.MainMulticastServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Listener implements ActionListener {
    MainMulticastServer multicastServer = null;


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "启动服务器":

                try {
                    if (multicastServer != null) {
                        multicastServer.close();
                    }
                    multicastServer = new MainMulticastServer();
                    multicastServer.start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                break;
            case "关闭服务器":
                try {
                    if (multicastServer == null) {
                        return;
                    }
                    multicastServer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }
}
