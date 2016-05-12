package graphics;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    JPanel midPanel;
    public MainWindow () {
        super("SafePass");
        container();
    }
    private void container() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        centerPoint.x -= 220;
        centerPoint.y -= 285;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(centerPoint);
        setSize(440, 570);
        setResizable(false);
        setIconImage(new ImageIcon("logo.png").getImage());
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel(new ImageIcon("SafePass.png"));
        panel.add(title, BorderLayout.NORTH);
        panel.setBackground(new Color(200, 240, 254));
        panel.setOpaque(true);
        midPanel = new JPanel();
        switchPanel((byte)0);
        panel.add(midPanel, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
    public void switchPanel (byte mode) {
        midPanel.removeAll();
        if ( mode == 0)
            new User().addPanels(midPanel);
        else
            new Account().addPanels(midPanel);
        midPanel.updateUI();
    }
}
