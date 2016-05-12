package Listeners;

import data.Profile;
import data.Token;
import data.Userdata;
import fileHandler.CryptoException;
import fileHandler.CryptoUtils;
import fileHandler.ReadWrite;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class EditConfirm implements ActionListener{
    private static Profile user;
    private static boolean prevStatus;
    JTextField name;
    JPanel panel;
    CardLayout card;
    JPasswordField pass, repeatPass;
    public EditConfirm ( JTextField name, JPasswordField pass, JPasswordField repeatPass, JPanel panel, CardLayout card ) {
        this.name = name;
        this.pass = pass;
        this.repeatPass = repeatPass;
        this.panel = panel;
        this.card = card;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean proceed = true;
        String oldPass = "";
        if ( name.getText() != null ) {
            if ( String.valueOf(pass.getPassword()).equals(String.valueOf(repeatPass.getPassword()))) {
                if ( pass.getPassword().length >= 8 ) {
                    try {
                        oldPass = user.getKey();
                        if (prevStatus == false && user.getFPActive() == false ) {
                        } else {
                            String msg = "";
                            if (new File("MasterFile.sp").isFile()) {
                                msg = "Enter master password:";
                            } else {
                                msg = "Create master password";
                            }
                            MasterFileHandler.showPasswordDialog(msg);
                            String password = PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword()));
                            user.setKey(password);
                            if (MasterFileHandler.getStatus()) {
                                if (prevStatus) {
                                    if (user.getFPActive()) {
                                        MasterFileHandler.updateProfile(user, true);
                                    } else {
                                        MasterFileHandler.removeUser(new Token(user.getSerial()), true);
                                    }
                                } else {
                                    MasterFileHandler.updateProfile(user, true);
                                }
                                proceed = true;
                            
                            } else {
                                JOptionPane.showMessageDialog(startUp.MainClass.window, "Unable to update master file!");
                                proceed = false;
                            }
                        }
                        if (proceed) {
                            String password = PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword()));
                            File temp = new File("temp");
                            user.setName(name.getText());
                            user.setAnswer("");
                            CryptoUtils.decrypt(oldPass, user.getFile(), temp);
                            user.setKey("");
                            Userdata.users.remove(new Token(user.getSerial()));
                            Userdata.users.put(new Token(user.getSerial()), user);
                            ReadWrite.writeObject(new File("User"), PasswordProcessor.passwordBuilder("insecure"), Userdata.users);
                            CryptoUtils.encrypt(password, temp, user.getFile());
                            temp.delete();
                            graphics.User.updateUsers();
                            name.setText("");
                            pass.setText("");
                            repeatPass.setText("");
                            card.show(panel, "Edit User");
                        }
                        ((JPanel) panel.getParent()).updateUI();
                    } catch (CryptoException ex) {
                        fileHandler.Exceptions.exceptions("EditConfirm", ex);
                    }
                } else
                    JOptionPane.showMessageDialog(startUp.MainClass.window, "Minimum 8 characters required for password!", "Error!", JOptionPane.WARNING_MESSAGE);
            } else
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Passwords do not match!", "Error!", JOptionPane.WARNING_MESSAGE);
        } else
            JOptionPane.showMessageDialog(startUp.MainClass.window, "Name cannot be left blank!",  "Error!", JOptionPane.WARNING_MESSAGE);
    }
    static void setUser (Profile user) {
        EditConfirm.user = user;
    }
    static void setPrevStatus ( boolean prevStatus ) {
        EditConfirm.prevStatus = prevStatus;
    }
}
