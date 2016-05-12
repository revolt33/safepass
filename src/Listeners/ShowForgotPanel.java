package Listeners;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ShowForgotPanel extends MouseAdapter{
    JPanel panel;
    CardLayout card;
    public ShowForgotPanel (JPanel panel, CardLayout card) {
        this.panel = panel;
        this.card = card;
    }
    @Override
    public void mouseClicked (MouseEvent me) {
        card.show(panel, "Forgot Password");
        ((JPanel)panel.getParent()).updateUI();
    }
}
