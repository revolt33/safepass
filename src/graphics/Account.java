package graphics;

import Listeners.AddAccount;
import Listeners.DeleteAccount;
import Listeners.EditAccount;
import Listeners.PanelListeners;
import Listeners.ShowAcount;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Account extends JPanel{
    CardLayout card;
    private static JComboBox view, edit;
    private static JPanel viewPanel, editPanel;
    void addPanels (JPanel parent) {
        JPanel child = new JPanel(new BorderLayout(0, 20));
        JPanel topChild = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
        card = new CardLayout();
        DecoratedPanel middleChild = new DecoratedPanel((byte)2, card);
        DecoratedPanel temp = decorate("Add Account", true);
        temp.addMouseListener(new PanelListeners(middleChild, card, (byte)5));
        topChild.add(temp);
        temp = decorate("View Account", true);
        temp.addMouseListener(new PanelListeners(middleChild, card,(byte)6));
        topChild.add(temp);
        temp = decorate("Edit Account", true);
        temp.addMouseListener(new PanelListeners(middleChild, card,(byte)7));
        topChild.add(temp);
        JPanel bottomchild = new JPanel(new FlowLayout(FlowLayout.CENTER));
        DecoratedPanel logout;
        logout = decorate("Logout", false);
        logout.addMouseListener(new PanelListeners(null, null, (byte)8));
        bottomchild.add(logout);
        child.add(topChild, BorderLayout.NORTH);
        middleChild.setOpaque(true);
        updateAccounts();
        addViewAccount(middleChild);
        addEditAccuount(middleChild);
        addAddAccount(middleChild);
        child.add(middleChild, BorderLayout.CENTER);
        child.add(bottomchild, BorderLayout.SOUTH);
        parent.add(child);
    }
    void addViewAccount (JPanel parent) {
        JPanel panel = new JPanel(new GridBagLayout());
        viewPanel = panel;
        JLabel account = new JLabel("Select Account");
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        account.setFont(font);
        JComboBox accounts;
        if ( view == null )
            view = new JComboBox();
        accounts = view;
        accounts.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("Your Password");
        pass.setFont(font);
        JTextField passField = new JTextField(15);
        passField.setPreferredSize(new Dimension(100, 35));
        passField.setEditable(false);
        JButton show = new JButton("Show");
        show.addActionListener(new ShowAcount(accounts, passField));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(account, gbc);
        gbc.insets.top = 2;
        gbc.gridy++;
        panel.add(accounts, gbc);
        gbc.gridy++;
        panel.add(pass, gbc);
        gbc.gridy++;
        panel.add(passField, gbc);
        gbc.gridy++;
        gbc.insets.bottom = 15;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(show, gbc);
        parent.add(panel, "View Account");
    }
    void addEditAccuount (JPanel parent ) {
        JPanel panel = new JPanel(new GridBagLayout());
        editPanel = panel;
        JLabel account = new JLabel("Select Account:");
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        account.setFont(font);
        JComboBox accounts;
        if ( edit == null )
            edit = new JComboBox();
        accounts = edit;
        accounts.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("New Password:");
        pass.setFont(font);
        JPasswordField passField = new JPasswordField(15);
        passField.setPreferredSize(new Dimension(100, 35));
        JLabel repeatPass = new JLabel("Repeat new password:");
        repeatPass.setFont(font);
        JPasswordField repeatPassField = new JPasswordField(15);
        repeatPassField.setPreferredSize(new Dimension(100, 35));
        JButton save = new JButton("Save");
        JButton delete = new JButton("Delete");
        delete.addActionListener(new DeleteAccount(accounts, parent, card));
        save.addActionListener(new EditAccount(accounts, passField, repeatPassField, parent, card));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(account, gbc);
        gbc.insets.top = 2;
        gbc.gridy++;
        panel.add(accounts, gbc);
        gbc.gridy++;
        panel.add(pass, gbc);
        gbc.gridy++;
        panel.add(passField, gbc);
        gbc.gridy++;
        panel.add(repeatPass, gbc);
        gbc.gridy++;
        panel.add(repeatPassField, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets.bottom = 15;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(delete, gbc);
        gbc.gridx++;
        panel.add(save, gbc);
        parent.add(panel, "Edit Account");
    }
    void addAddAccount ( JPanel parent ) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel account = new JLabel("Account Name:");
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        account.setFont(font);
        JTextField accounts = new JTextField(15);
        accounts.setPreferredSize(new Dimension(100, 35));
        JLabel pass = new JLabel("New Password:");
        pass.setFont(font);
        JPasswordField passField = new JPasswordField(15);
        passField.setPreferredSize(new Dimension(100, 35));
        JLabel repeatPass = new JLabel("Repeat new password:");
        repeatPass.setFont(font);
        JPasswordField repeatPassField = new JPasswordField(15);
        repeatPassField.setPreferredSize(new Dimension(100, 35));
        JButton add = new JButton("Add");
        add.addActionListener(new AddAccount(accounts, passField, repeatPassField, parent, card));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(account, gbc);
        gbc.insets.top = 2;
        gbc.gridy++;
        panel.add(accounts, gbc);
        gbc.gridy++;
        panel.add(pass, gbc);
        gbc.gridy++;
        panel.add(passField, gbc);
        gbc.gridy++;
        panel.add(repeatPass, gbc);
        gbc.gridy++;
        panel.add(repeatPassField, gbc);
        gbc.gridy++;
        gbc.insets.bottom = 15;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(add, gbc);
        parent.add(panel, "Add Account");
    }
    DecoratedPanel decorate (String text, boolean up) {
        DecoratedPanel panel = new DecoratedPanel((byte)1);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel label = new JLabel(text);
        if (up)
            label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        else
            label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);
        panel.setOpaque(true);
        return panel;
    }
    public static void updateAccounts () {
        data.Profile user = data.Userdata.getUser();
        if ( user.getAccounts() > 0) {
            Object[] accounts = getAccountList();
            if ( editPanel == null || viewPanel == null ) {
                view = new JComboBox(accounts);
                edit = new JComboBox(accounts);
                
            } else {
                view.removeAllItems();
                edit.removeAllItems();
                for (Object o: accounts) {
                    view.addItem(o);
                    edit.addItem(o);
                }
                editPanel.updateUI();
                viewPanel.updateUI();
            }
        }
    }
    static Object[] getAccountList () {
        ArrayList accounts = new ArrayList();
        if (data.Userdata.getUser().getAccounts() > 0) {
            Set list = data.AccountHolder.getAccountList().entrySet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Map.Entry<data.Token, data.Account> entry = (Map.Entry<data.Token, data.Account>) it.next();
                accounts.add(entry.getValue());
            }
        }
        return accounts.toArray();
    }
}
