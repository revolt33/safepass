package Listeners;

import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class EditProfile implements ActionListener{
    private JPasswordField pass, repeatPass;
    private JComboBox<Object> users;
    private JCheckBox rp;
    private JPanel panel;
    private CardLayout card;
    public EditProfile (JComboBox users, JPasswordField pass, JPasswordField repeatPass, JCheckBox rp, JPanel panel, CardLayout card) {
        this.pass = pass;
        this.repeatPass = repeatPass;
        this.rp = rp;
        this.users = users;
        this.card = card;
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (pass.getPassword().length >= 8) {
        if (String.valueOf(pass.getPassword()).equals(String.valueOf(repeatPass.getPassword()))) {
            data.Profile user = (data.Profile)users.getSelectedItem();
            String passwd = PasswordProcessor.passwordBuilder(String.valueOf(pass.getPassword()));
            byte status = startUp.Check.checkForFiles(user.getFile(), passwd);
            if (status == 1 || status == 2) {
                user.setKey(passwd);
                if ( user.getFPActive() ) {
                    if ( rp.isSelected()) {
                        EditConfirm.setPrevStatus(true);
                        SecurityQuestion.setStatus(true);
                        SecurityQuestion.setUser(user);
                        card.show(panel, "Security Question");
                    } else {
                        EditConfirm.setPrevStatus(true);
                        user.setFPActive(false);
                        EditConfirm.setUser(user);
                        card.show(panel, "Edit Confirm");
                    }
                } else {
                    if ( rp.isSelected() ) {
                        user.setFPActive(true);
                        EditConfirm.setPrevStatus(false);
                        SecurityQuestion.setStatus(true);
                        SecurityQuestion.setUser(user);
                        card.show(panel, "Security Question");
                    } else {
                        EditConfirm.setPrevStatus(false);
                        EditConfirm.setUser(user);
                        card.show(panel, "Edit Confirm");
                    }
                }
                pass.setText("");
                repeatPass.setText("");
                rp.setSelected(false);
                ((JPanel)panel.getParent()).updateUI();
            } else
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Authentication Problem!", "Error!", JOptionPane.WARNING_MESSAGE);
        } } else
            JOptionPane.showMessageDialog(startUp.MainClass.window, "Minimum 8 character password is required!", "Error!", JOptionPane.WARNING_MESSAGE);
    }

}
