package Listeners;

import data.Token;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShowResetPanel implements ActionListener{

    JPanel panel;
    CardLayout card;
    JComboBox<Object> users;
    JTextField ans;
    public ShowResetPanel ( JPanel panel, CardLayout card, JComboBox users, JTextField ans ) {
        this.ans = ans;
        this.card = card;
        this.panel = panel;
        this.users = users;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        MasterFileHandler.showPasswordDialog("Enter Master Password:");
        if ( users.getItemCount() > 0)
        if (MasterFileHandler.getStatus() ) {
            data.Profile user = MasterFileHandler.getUser(new Token(((data.Profile)users.getSelectedItem()).getSerial()), false);
            if ( ans.getText().equals(user.getAnswer())) {
                ans.setText("");
                ResetPassword.setUser(user);
                card.show(panel, "Reset Password");
            } else
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Incorrect Answer!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
