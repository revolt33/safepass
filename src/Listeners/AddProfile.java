package Listeners;

import data.Userdata;
import javax.swing.*;
import java.awt.event.*;
import startUp.MainClass;
import data.*;
import java.awt.CardLayout;
import java.io.File;
import java.util.TreeMap;

public class AddProfile implements ActionListener{
    JPasswordField pass, repeat;
    JTextField name;
    JCheckBox box;
    CardLayout card;
    JPanel panel;
    public AddProfile (JTextField name, JPasswordField pass, JPasswordField repeat, JCheckBox box, JPanel panel, CardLayout card) {
        this.box = box;
        this.name = name;
        this.pass = pass;
        this.repeat = repeat;
        this.card = card;
        this.panel = panel;
    }
    @Override
    public void actionPerformed (ActionEvent ae) {
        if ( pass.getPassword().length >= 8 )
        if (String.valueOf(pass.getPassword()).equals(String.valueOf(repeat.getPassword()))) {
            Userdata.userCount++;
            File file =  new File("Account"+File.separator+"user"+Userdata.userCount);
            Profile profile = new Profile(name.getText(),file, Userdata.userCount);
            fileHandler.ReadWrite.createEmptyFile(file, PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword())));
            if (box.isSelected()) {
                profile.setFPActive(true);
                profile.setKey(PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword())));
                SecurityQuestion.setUser(profile);
                SecurityQuestion.setStatus(false);
                card.show(panel, "Security Question");
                ((JPanel)panel.getParent()).updateUI();
            } else {
                profile.setFPActive(false);
                TreeMap<Token, Profile> set = null;
                if (Userdata.status) {
                    try {
                        set = (TreeMap<Token, Profile>) fileHandler.ReadWrite.readObject(new File("User"), "insecureinsecure");
                    } catch (ClassNotFoundException ex) {
                    }
                    set.put(new Token(profile.getSerial()), profile);
                } else {
                    set = new TreeMap<>(new Token(0));
                    set.put(new Token(profile.getSerial()), profile);
                }
                Userdata.users = set;
                fileHandler.ReadWrite.writeObject(new File("User"), "insecureinsecure", set);
                data.Userdata.status = true;
                graphics.User.updateUsers();
                JOptionPane.showMessageDialog(MainClass.window, "Account Added Successfully!");
            }
            name.setText("");
            pass.setText("");
            repeat.setText("");
            box.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(MainClass.window, "Passwords do Not Match!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
}