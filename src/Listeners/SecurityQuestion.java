package Listeners;

import data.Profile;
import data.Token;
import data.Userdata;
import java.awt.CardLayout;
import java.awt.event.*;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SecurityQuestion implements ActionListener{

    private static Profile user;
    private static boolean status;
    JComboBox<Object> ques;
    JTextField ans;
    JPanel panel;
    CardLayout card;
    public SecurityQuestion ( JComboBox ques, JTextField ans, JPanel panel, CardLayout card) {
        this.ans = ans;
        this.card = card;
        this.panel = panel;
        this.ques = ques;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( ans.getText() != null ) {
        user.setQuestion(((data.SecurityQuestion) ques.getSelectedItem()).getSerial());
        user.setAnswer(ans.getText());
        if ( status ) {
            EditConfirm.setUser(user);
            card.show(panel, "Edit Confirm");
        } else {
            File file = new File("MasterFile.sp");
            String msg = "";
            if ( file.isFile())
                msg = "Enter master password:";
            else
                msg = "Create master password:";
            MasterFileHandler.showPasswordDialog(msg);
            MasterFileHandler.updateProfile(user, true);
            fileHandler.ReadWrite.createEmptyFile(user.getFile(), user.getKey());
            user.setKey("");
            user.setAnswer("");
            data.Userdata.users.put(new Token(user.getSerial()), user);
            fileHandler.ReadWrite.writeObject(new File("User"), "insecureinsecure", data.Userdata.users);
            Userdata.userCount++;
            Userdata.status = true;
            graphics.User.updateUsers();
            card.show(panel, "Add User");
        }
        ans.setText("");
        ((JPanel)panel.getParent()).updateUI();
        } else
            JOptionPane.showMessageDialog(startUp.MainClass.window, "Answer cannot be left blank!", "Error!", JOptionPane.WARNING_MESSAGE);
    }
    static void setUser ( Profile user ) {
        SecurityQuestion.user = user;
    }
    static void setStatus ( boolean status ) {
        SecurityQuestion.status = status;
    }
}