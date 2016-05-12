package Listeners;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import data.Profile;
import java.io.File;
import startUp.*;

public class DeleteProfile implements ActionListener{
    JComboBox<Object> users;
    JPasswordField pass, repeatPass;
    public DeleteProfile (JComboBox users, JPasswordField pass, JPasswordField repeatPass) {
        this.pass = pass;
        this.repeatPass = repeatPass;
        this.users = users;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( users.getSelectedItem() != null )
        if ( String.valueOf(pass.getPassword()).equals(String.valueOf(repeatPass.getPassword()))) {
            if ( pass.getPassword().length >= 8) {
                Profile user = (Profile) users.getSelectedItem();
                byte status = Check.checkForFiles(user.getFile(), PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword())));
                if ( status == 1 || status == 2 ) {
                    user.getFile().delete();
                    data.Userdata.users.remove(new data.Token(user.getSerial()));
                    fileHandler.ReadWrite.writeObject(new File("User"), PasswordProcessor.passwordBuilder("insecure"), data.Userdata.users);
                    graphics.User.updateUsers();
                    pass.setText("");
                    repeatPass.setText("");
                    JOptionPane.showMessageDialog(MainClass.window, "Account removed successfully!");
                } else if ( status == 0 ) {
                    JOptionPane.showMessageDialog(MainClass.window, "Incorrect Password, unable to remove account!");
                }
            } else
                JOptionPane.showMessageDialog(MainClass.window, "Minimum 8 characters required!", "Error!", JOptionPane.WARNING_MESSAGE);
        } else
            JOptionPane.showMessageDialog(MainClass.window, "Passwords do not match!", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
