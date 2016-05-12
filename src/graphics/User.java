package graphics;

import Listeners.AddProfile;
import Listeners.DeleteProfile;
import Listeners.EditProfile;
import Listeners.EditConfirm;
import Listeners.ExportProfile;
import Listeners.ImportAccount;
import Listeners.Login;
import Listeners.PanelListeners;
import Listeners.ResetPassword;
import Listeners.ShowForgotPanel;
import Listeners.ShowResetPanel;
import Listeners.UpdateQuestion;
import data.SecurityQuestion;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.*;

public class User extends JPanel{
    private static JComboBox edit, login, recovery, export;
    private static JPanel editPanel, loginPanel, recoveryPanel, exportPanel;
    CardLayout card;
    public static data.SecurityQuestion[] questions = {new SecurityQuestion("What was your childhood nickname?", 1), new SecurityQuestion("What is the name of your favorite childhood friend?", 2),
        new SecurityQuestion("What school did you attend for sixth grade?", 3), new SecurityQuestion("In what city does your nearest sibling live?", 4), 
        new SecurityQuestion("What is the name of a college you applied to but didn't attend?", 5), new SecurityQuestion("In what city or town was your first job?", 6)};
    void addPanels (JPanel parent) {
        JPanel child = new JPanel(new BorderLayout(0, 20));
        JPanel topChild = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
        card = new CardLayout();
        DecoratedPanel middleChild = new DecoratedPanel((byte)2, card);
        DecoratedPanel temp = decorate("Add User");
        temp.addMouseListener(new PanelListeners(middleChild, card, (byte)0));
        topChild.add(temp);
        temp = decorate("Login Panel");
        temp.addMouseListener(new PanelListeners(middleChild, card,(byte)1));
        topChild.add(temp);
        temp = decorate("Edit User");
        temp.addMouseListener(new PanelListeners(middleChild, card,(byte)2));
        topChild.add(temp);
        JPanel bottomchild = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        DecoratedPanel Import, Export;
        Import = decorate("Import Account");
        Export = decorate("Export Account");
        Import.addMouseListener(new ImportAccount());
        Export.addMouseListener(new PanelListeners(middleChild, card, (byte)3));
        bottomchild.add(Import);
        bottomchild.add(Export);
        child.add(topChild, BorderLayout.NORTH);
        middleChild.setOpaque(true);
        updateUsers();
        addLogin(middleChild);
        addNewUser(middleChild);
        addEditUser(middleChild);
        addEditConfirm(middleChild);
        addSecurityQuestion(middleChild);
        addForgotPassword(middleChild);
        addResetPassword(middleChild);
        addExportPanel(middleChild);
        child.add(middleChild, BorderLayout.CENTER);
        child.add(bottomchild, BorderLayout.SOUTH);
        parent.add(child);
    }
    void addLogin (JPanel parent) {
        JPanel panel = new JPanel( new GridBagLayout());
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        JLabel userName = new JLabel("Username:");
        userName.setFont(font);
        if (login == null ) {
            login = new JComboBox();
        }
        JComboBox users = login;
        users.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("Password:");
        pass.setFont(font);
        JPasswordField password = new JPasswordField(15);
        password.setPreferredSize(new Dimension(100, 35));
        JButton button = new JButton("Login");
        button.addActionListener(new Login(users, password));
        JLabel fpLabel = new JLabel("forgot password?");
        fpLabel.addMouseListener(new ShowForgotPanel(parent, card));
        font = new Font(Font.SERIF, Font.ITALIC, 14);
        fpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fpLabel.setFont(font);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 2, 0);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userName, gbc);
        gbc.insets.top = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(users, gbc);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(pass, gbc);
        gbc.gridy = 3;
        panel.add(password, gbc);
        gbc.gridy = 4;
        panel.add(fpLabel, gbc);
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets.bottom = 20;
        panel.add(button, gbc);
        loginPanel = panel;
        parent.add(panel, "login");
    }
    void addNewUser (JPanel parent) {
        JPanel panel = new JPanel(new GridBagLayout());
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        JLabel name = new JLabel("Username:");
        name.setFont(font);
        JTextField nameValue = new JTextField(15);
        nameValue.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("Your Password:");
        pass.setFont(font);
        JPasswordField passField = new JPasswordField(15);
        passField.setPreferredSize(new Dimension(200, 35));
        JLabel repeatPass = new JLabel("Repeat Password:");
        repeatPass.setFont(font);
        JPasswordField repeatPassField = new JPasswordField(15);;
        repeatPassField.setPreferredSize(new Dimension(100, 35));
        JCheckBox rp = new JCheckBox("Remember Password");
        JButton button = new JButton("Create");
        button.addActionListener(new AddProfile(nameValue, passField, repeatPassField, rp, parent, card));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 2, 0);
        panel.add(name, gbc);
        gbc.gridy = 1;
        gbc.insets.top = 2;
        panel.add(nameValue, gbc);
        gbc.gridy = 2;
        panel.add(pass, gbc);
        gbc.gridy = 3;
        panel.add(passField, gbc);
        gbc.gridy = 4;
        panel.add(repeatPass, gbc);
        gbc.gridy = 5;
        panel.add(repeatPassField, gbc);
        gbc.gridy = 6;
        panel.add(rp, gbc);
        gbc.gridy = 7;
        gbc.insets.bottom = 10;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(button, gbc);
        parent.add(panel, "Add User");
    }
    void addEditUser (JPanel parent) {
        JPanel panel = new JPanel(new GridBagLayout());
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        JLabel name = new JLabel("Username:");
        name.setFont(font);
        if ( edit == null ) {
            edit = new JComboBox();
        }
        JComboBox nameValue = edit;
        nameValue.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("Your Password:");
        pass.setFont(font);
        JPasswordField passField = new JPasswordField(15);
        passField.setPreferredSize(new Dimension(200, 35));
        JLabel repeatPass = new JLabel("Repeat Password:");
        repeatPass.setFont(font);
        JPasswordField repeatPassField = new JPasswordField(15);;
        repeatPassField.setPreferredSize(new Dimension(100, 35));
        JCheckBox rp = new JCheckBox("Remember Password");
        JButton save = new JButton("Edit");
        save.addActionListener(new EditProfile(nameValue, passField, repeatPassField, rp, parent, card));
        JButton delete = new JButton("Delete");
        delete.addActionListener(new DeleteProfile(edit, passField, repeatPassField));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 2, 0);
        panel.add(name, gbc);
        gbc.gridy = 1;
        gbc.insets.top = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameValue, gbc);
        gbc.gridy = 2;
        panel.add(pass, gbc);
        gbc.gridy = 3;
        panel.add(passField, gbc);
        gbc.gridy = 4;
        panel.add(repeatPass, gbc);
        gbc.gridy = 5;
        panel.add(repeatPassField, gbc);
        gbc.gridy = 6;
        panel.add(rp, gbc);
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.bottom = 10;
        gbc.gridwidth = 1;
        panel.add(delete, gbc);
        gbc.gridx = 1;
        panel.add(save, gbc);
        editPanel = panel;
        parent.add(panel, "Edit User");
    }
    void addEditConfirm ( JPanel parent ){
        JPanel panel = new JPanel(new GridBagLayout());
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        JLabel name = new JLabel("Username:");
        name.setFont(font);
        JTextField nameValue = new JTextField(15);
        nameValue.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("Your New Password:");
        pass.setFont(font);
        JPasswordField passField = new JPasswordField(15);
        passField.setPreferredSize(new Dimension(200, 35));
        JLabel repeatPass = new JLabel("Repeat Password:");
        repeatPass.setFont(font);
        JPasswordField repeatPassField = new JPasswordField(15);;
        repeatPassField.setPreferredSize(new Dimension(100, 35));
        JButton button = new JButton("Save");
        button.addActionListener(new EditConfirm(nameValue, passField, repeatPassField, parent, card));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 2, 0);
        panel.add(name, gbc);
        gbc.gridy = 1;
        gbc.insets.top = 2;
        panel.add(nameValue, gbc);
        gbc.gridy = 2;
        panel.add(pass, gbc);
        gbc.gridy = 3;
        panel.add(passField, gbc);
        gbc.gridy = 4;
        panel.add(repeatPass, gbc);
        gbc.gridy = 5;
        panel.add(repeatPassField, gbc);
        gbc.gridy = 6;
        gbc.insets.bottom = 10;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(button, gbc);
        parent.add(panel, "Edit Confirm");
    }
    void addSecurityQuestion ( JPanel parent ) {
        JPanel panel = new JPanel(new GridBagLayout());
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        JLabel ques = new JLabel("Choose your security question:");
        ques.setFont(font);
        JComboBox question = new JComboBox(questions);
        question.setPreferredSize(new Dimension(100, 35));
        JLabel ans  = new JLabel("Answer");
        ans.setFont(font);
        JTextField answer = new JTextField(15);
        answer.setPreferredSize(new Dimension(100, 35));
        JButton proceed = new JButton( "Proceed" );
        proceed.addActionListener(new Listeners.SecurityQuestion(question, answer, parent, card));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ques, gbc);
        gbc.gridy++;
        panel.add(question, gbc);
        gbc.gridy++;
        panel.add(ans, gbc);
        gbc.gridy++;
        panel.add(answer, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(proceed, gbc);
        parent.add(panel, "Security Question");
    }
    void addResetPassword (JPanel parent ) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label, pass, repeatPass;
        JPasswordField passField, repeatPassField;
        label = new JLabel("Reset Password");
        Font font = new Font(Font.SERIF, Font.BOLD, 19);
        label.setFont(font);
        pass = new JLabel("New Password:");
        repeatPass = new JLabel("Repeat Password:");
        font = new Font(Font.SERIF, Font.BOLD, 17);
        pass.setFont(font);
        repeatPass.setFont(font);
        passField = new JPasswordField(15);
        repeatPassField = new JPasswordField(15);
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ResetPassword(passField, repeatPassField, parent, card));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 2, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);
        gbc.gridy++;
        gbc.insets.top = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(pass, gbc);
        gbc.gridy++;
        panel.add(passField, gbc);
        gbc.gridy++;
        panel.add(repeatPass, gbc);
        gbc.gridy++;
        panel.add(repeatPassField, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy++;
        gbc.insets.bottom = 10;
        panel.add(reset, gbc);
        parent.add(panel, "Reset Password");
    }
    void addForgotPassword ( JPanel parent ) {
        JPanel panel = new JPanel(new GridBagLayout());
        recoveryPanel = panel;
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        JLabel user = new JLabel("Username:");
        user.setFont(font);
        if (recovery == null)
            recovery = new JComboBox();
        JComboBox users = recovery;
        users.setPreferredSize(new Dimension(100, 35));
        JLabel ques = new JLabel("Security Question:");
        ques.setFont(font);
        JLabel question = new JLabel(questions[0].toString());
        question.setToolTipText(questions[0].toString());
        JLabel answer = new JLabel("Answer:");
        answer.setFont(font);
        font = new Font(Font.SERIF, Font.ITALIC, 14);
        question.setFont(font);
        JTextField field = new JTextField(15);
        field.setPreferredSize(new Dimension(100, 35));
        JButton next = new JButton("Proceed");
        next.addActionListener(new ShowResetPanel(parent, card, users, field));
        users.addActionListener(new UpdateQuestion(users, question));
        users.addMouseListener(new UpdateQuestion(users, question));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 2, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(user, gbc);
        gbc.insets.top = 2;
        gbc.gridy++;
        panel.add(users, gbc);
        gbc.gridy++;
        panel.add(ques, gbc);
        gbc.gridy++;
        panel.add(question, gbc);
        gbc.gridy++;
        panel.add(answer, gbc);
        gbc.gridy++;
        panel.add(field, gbc);
        gbc.gridy++;
        gbc.insets.bottom = 10;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(next, gbc);
        parent.add(panel, "Forgot Password");
    }
    void addExportPanel ( JPanel parent ) {
        JPanel panel = new JPanel(new GridBagLayout());
        exportPanel = panel;
        JLabel select = new JLabel("Select Account:");
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        select.setFont(font);
        if ( export == null ) {
            export = new JComboBox();
        }
        JComboBox users = export;
        users.setPreferredSize(new Dimension(200, 35));
        JButton button = new JButton("Export");
        button.addActionListener(new ExportProfile(users));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(select, gbc);
        gbc.gridy++;
        panel.add(users, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(button, gbc);
        parent.add(panel, "Export");
    }
    DecoratedPanel decorate (String text) {
        DecoratedPanel panel = new DecoratedPanel((byte)1);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);
        panel.setOpaque(true);
        return panel;
    }
    public static void updateUsers () {
        if (editPanel == null || loginPanel == null || recoveryPanel == null || exportPanel == null ) {
            if (data.Userdata.status){
                Object[] userList = getUserList();
                edit = new JComboBox(userList);
                login = new JComboBox(userList);
                recovery = new JComboBox(getRecoveryList());
                export = new JComboBox(getUserList());
            }
        } else {
            if (data.Userdata.status) {
                edit.removeAllItems();
                login.removeAllItems();
                UpdateQuestion.status = false;
                recovery.removeAllItems();
                UpdateQuestion.status = true;
                export.removeAllItems();
                for (Object o: getUserList()) {
                    edit.addItem(o);
                    login.addItem(o);
                    export.addItem(o);
                }
                for ( Object o: getRecoveryList() )
                    recovery.addItem(o);
                editPanel.updateUI();
                loginPanel.updateUI();
                recoveryPanel.updateUI();
                exportPanel.updateUI();
            }
        }
    }
    private static Object[] getUserList () {
        ArrayList users = new ArrayList();
        Set list = data.Userdata.users.entrySet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map.Entry<data.Token, data.Profile> entry = (Map.Entry<data.Token, data.Profile>) it.next();
            users.add(entry.getValue());
        }
        return users.toArray();
    }
    private static Object[] getRecoveryList () {
        ArrayList users = new ArrayList();
        Set list = data.Userdata.users.entrySet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map.Entry<data.Token, data.Profile> entry = (Map.Entry<data.Token, data.Profile>) it.next();
            if ( entry.getValue().getFPActive())
                users.add(entry.getValue());
        }
        return users.toArray();
    }
}