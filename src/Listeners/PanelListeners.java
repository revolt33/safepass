package Listeners;

import java.awt.event.MouseAdapter;
import graphics.DecoratedPanel;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PanelListeners extends MouseAdapter{
    byte no;
    DecoratedPanel panel;
    CardLayout card;
    
    public PanelListeners (DecoratedPanel panel, CardLayout card, byte no) {
        this.no = no;
        this.panel = panel;
        this.card = card;
    }
    @Override
    public void mouseClicked (MouseEvent me) {
        boolean update = true;
        switch (no) {
            case (byte)0:
                card.show(panel, "Add User");
                break;
            case (byte)1:
                card.show(panel, "login");
                break;
            case (byte)2:
                card.show(panel, "Edit User");
                break;
            case (byte)3:
                card.show(panel, "Export");
                break;
            case (byte)4:
                break;
            case (byte)5:
                card.show(panel, "Add Account");
                break;
            case (byte)6:
                card.show(panel, "View Account");
                break;
            case (byte)7:
                card.show(panel, "Edit Account");
                break;
            case (byte)8:
                startUp.MainClass.window.switchPanel((byte)0);
                update = false;
                data.Userdata.setUser(null);
                break;
        }
        if ( update )
        ((JPanel)panel.getParent()).updateUI();
    }
}
