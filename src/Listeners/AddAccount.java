package Listeners;

import data.Account;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class AddAccount implements ActionListener{

    JTextField name;
    JPanel panel;
    CardLayout card;
    JPasswordField passField, repeatPassField;
    public AddAccount (JTextField name, JPasswordField passField, JPasswordField repeatPassField, JPanel panel, CardLayout card ) {
        this.name = name;;
        this.passField = passField;
        this.repeatPassField = repeatPassField;
        this.panel = panel;
        this.card = card;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( String.valueOf(passField.getPassword()).equals(String.valueOf(repeatPassField.getPassword()))) {
            if ( passField.getPassword().length > 0) {
                if ( name.getText().length() > 0 && name.getText().length() < 25) {
                    data.Account account = new Account(name.getText(), String.valueOf(passField.getPassword()));
                    account.setSerial(data.Userdata.getUser().getAccounts() + 1);
                    data.AccountHolder.addAccount(account);
                    name.setText("");
                    passField.setText("");
                    repeatPassField.setText("");
                    ((JPanel)panel.getParent()).updateUI();
                } else
                    JOptionPane.showMessageDialog(startUp.MainClass.window, "Please enter a valid account name!",  "Error!",JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Please enter a valid password!",  "Error!",JOptionPane.WARNING_MESSAGE);
            }
        } else
            JOptionPane.showMessageDialog(startUp.MainClass.window, "Passwords do not match!", "Error!", JOptionPane.WARNING_MESSAGE);
    }
    
}
