package Listeners;

import data.Token;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DeleteAccount implements ActionListener{

    JComboBox<Object> accounts;
    JPanel panel;
    CardLayout card;
    public DeleteAccount (JComboBox accounts, JPanel panel, CardLayout card) {
        this.accounts = accounts;
        this.card = card;
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        int option = JOptionPane.showConfirmDialog(startUp.MainClass.window,"Are you sure you want to delete\nthis account: "+((data.Account)accounts.getSelectedItem()).getAccount()+"?", "Alert!",JOptionPane.YES_NO_OPTION);
        if ( option == JOptionPane.YES_OPTION ) {
            data.AccountHolder.removeAccount(new Token(((data.Account)accounts.getSelectedItem()).getSerial()));
            ((JPanel)panel.getParent()).updateUI();
        }
    }
}