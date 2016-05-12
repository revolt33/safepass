package Listeners;

import fileHandler.CryptoException;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ResetPassword implements ActionListener{

    JPasswordField pass, repeatPass;
    JPanel panel;
    CardLayout card;
    private static data.Profile user;
    
    public ResetPassword ( JPasswordField pass, JPasswordField repeatPass, JPanel panel, CardLayout card ) {
        this.card = card;
        this.panel = panel;
        this.pass = pass;
        this.repeatPass = repeatPass;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( String.valueOf(pass.getPassword()).equals(String.valueOf(repeatPass.getPassword()))) {
            if ( pass.getPassword().length >= 8 ) {
                String password = PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword()));
                File temp = new File("temp");
                try {
                    fileHandler.CryptoUtils.decrypt(user.getKey(), user.getFile(), temp);
                    fileHandler.CryptoUtils.encrypt(password, temp, user.getFile());
                    temp.delete();
                } catch (CryptoException ex) {
                    fileHandler.Exceptions.exceptions("ResetPassword", ex);
                }
                user.setKey(password);
                MasterFileHandler.updateProfile(user, true);
                pass.setText("");
                repeatPass.setText("");
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Account reset successful!");
                card.show(panel, "login");
            } else
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Minimum 8 character password required!");
        } else
            JOptionPane.showMessageDialog(startUp.MainClass.window, "Passwords do not match!");
    }
    static void setUser ( data.Profile user ) {
        ResetPassword.user = user;
    }
}
