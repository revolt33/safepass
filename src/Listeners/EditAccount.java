package Listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class EditAccount implements ActionListener{

    JComboBox<Object> accounts;
    JPasswordField passField, repeatPassField;
    JPanel panel;
    CardLayout card;
    
    public EditAccount ( JComboBox accounts, JPasswordField passField, JPasswordField repeatPassField, JPanel panel, CardLayout card) {
        this.accounts = accounts;
        this.passField = passField;
        this.repeatPassField = repeatPassField;
        this.card = card;
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (accounts.getItemCount() > 0) {
            if (String.valueOf(passField.getPassword()).equals(String.valueOf(repeatPassField.getPassword()))) {
                if (passField.getPassword().length > 0) {
                            data.Account account = (data.Account) accounts.getSelectedItem();
                            account.setPassword(String.valueOf(passField.getPassword()));
                            data.AccountHolder.updateAccount(account);
                            ((JPanel)panel.getParent()).updateUI();
                            passField.setText("");
                            repeatPassField.setText("");
                        }
                } else {
                    JOptionPane.showMessageDialog(startUp.MainClass.window, "Password cannot be left blank!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Passwords do not match!", "Error!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
