package serverUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.LayoutPath;

public class ServerUI {
    public static JFrame serverFrame = null;
    JPanel serverMainPanel = null;
    JPanel serverEastPanel = null;

    public ServerUI() {
        initServerFrame();
        initServerMainPanel();
        initServerEastPanel();
        isShowMainWindow(true);

    }



    private synchronized void isShowMainWindow(boolean isShow) {
        serverFrame.setVisible(isShow);
    }


    private synchronized void initServerFrame() {
        serverFrame = new JFrame("五子棋服务器");
        serverFrame.setSize(780, 635);
        serverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        serverFrame.setLocationRelativeTo(null);
        serverFrame.setResizable(false);
        serverFrame.setLayout(new BorderLayout());
        serverFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }



    private synchronized void initServerMainPanel() {
        serverMainPanel = new JPanel();
        serverMainPanel.setLayout(new BorderLayout());
        serverMainPanel.setSize(280, 635);
        serverFrame.add(BorderLayout.EAST, serverMainPanel);
    }

    private synchronized void initServerEastPanel() {
        serverEastPanel = new JPanel();
        serverEastPanel.setLayout(new GridLayout(6,1));

        JButton startServer = new JButton("启动服务器");
        JButton closeServer = new JButton("关闭服务器");

        startServer.setBackground(new Color(240,240,240));
        closeServer.setBackground(new Color(240,240,240));

        serverEastPanel.add(startServer);
        serverEastPanel.add(closeServer);

        Listener listener = new Listener();

        startServer.addActionListener(listener);
        closeServer.addActionListener(listener);

        serverMainPanel.add(serverEastPanel);
    }




}
