package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ShowAcount implements ActionListener{

    JComboBox<Object> accounts;
    JTextField pass;
    public ShowAcount (JComboBox accounts, JTextField pass ) {
        this.accounts = accounts;
        this.pass = pass;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        data.Account account = (data.Account) accounts.getSelectedItem();
        pass.setText(account.getPassword());
    }
    
}
