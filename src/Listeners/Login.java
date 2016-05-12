package Listeners;

import data.Account;
import data.Token;
import java.awt.event.*;
import java.util.TreeMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Login implements ActionListener{
    JComboBox<data.Profile> name;
    JPasswordField pass;
    public Login (JComboBox name, JPasswordField pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (pass.getPassword().length >= 8) {
            data.Profile user = data.Userdata.users.get(new Token(((data.Profile) name.getSelectedItem()).getSerial()));
            byte status = startUp.Check.checkForFiles(user.getFile(), PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword())));
            switch (status) {
                case (byte) 0:
                    JOptionPane.showMessageDialog(startUp.MainClass.window, "Incorrect Password");
                    break;
                case (byte) 1:
                    update(user);
                    startUp.MainClass.window.switchPanel((byte) 1);
                    break;
                case (byte) 2:
                    update(user);
                    startUp.MainClass.window.switchPanel((byte) 1);
                    break;
                case (byte) 3:
                    JOptionPane.showMessageDialog(startUp.MainClass.window, "File do no exists");
                    break;
            }
            pass.setText("");
        } else
            JOptionPane.showMessageDialog(startUp.MainClass.window, "Minimum 8 character password required!", "Error!", JOptionPane.WARNING_MESSAGE);
    }
    void update(data.Profile user) {
        user.setKey(PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword())));
        data.Userdata.setUser(user);
        if (user.getAccounts() > 0) {
            try {
                data.AccountHolder.setAccountList(((TreeMap<Token, Account>) fileHandler.ReadWrite.readObject(user.getFile(), user.getKey())));
            } catch (ClassNotFoundException ex) {
                fileHandler.Exceptions.exceptions("Login", ex);
            }
        }
    }
}
