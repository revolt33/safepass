package Listeners;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.*;
import data.*;
import java.awt.Insets;
import java.io.File;
import java.util.*;

public class MasterFileHandler {

    private static String password;
    private static byte status;

    public static void updateProfile(Profile user, boolean signal) {
        if (status == 1 || status == 3) {
            TreeMap<Token, Profile> map = new TreeMap<>(new Token(0));
            map.put(new Token(user.getSerial()), user);
            fileHandler.ReadWrite.writeObject(new File("MasterFile.sp"), password, map);
        } else if (status == 2) {
            TreeMap<Token, Profile> map = new TreeMap<>();
            try {
                map = (TreeMap<Token, Profile>) fileHandler.ReadWrite.readObject(new File("MasterFile.sp"), password);
            } catch (ClassNotFoundException ex) {
            }
            if (map.containsKey(new Token(user.getSerial()))) {
                map.remove(new Token(user.getSerial()));
            }
            map.put(new Token(user.getSerial()), user);
            fileHandler.ReadWrite.writeObject(new File("MasterFile.sp"), password, map);
        }
        if (signal)
            password = "";
    }

    static void showPasswordDialog(String msg) {
        JDialog dialog = new JDialog(startUp.MainClass.window, "Security", true);
        dialog.setLayout(new GridBagLayout());
        JLabel label = new JLabel(msg);
        JPasswordField pass = new JPasswordField(10);
        JButton button = new JButton("Ok");
        button.addActionListener(new MasterPassword(pass, dialog));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        dialog.add(label, gbc);
        gbc.gridy = 1;
        dialog.add(pass, gbc);
        gbc.gridy = 2;
        dialog.add(button, gbc);
        dialog.pack();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        Dimension size = dialog.getSize();
        size.height = size.height / 2;
        size.width = size.width / 2;
        centerPoint.x = centerPoint.x - size.width;
        centerPoint.y = centerPoint.y - size.height;
        dialog.setLocation(centerPoint);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    static void setPassword(String password, byte status) {
        MasterFileHandler.password = password;
        MasterFileHandler.status = status;
    }
    static void removeUser ( Token token, boolean signal ) {
        TreeMap<Token, Profile> map = new TreeMap<>(new Token(0));
        try {
            map = ( TreeMap<Token, Profile> ) fileHandler.ReadWrite.readObject(new File("MasterFile.sp"), password);
        } catch (ClassNotFoundException ex) {
            fileHandler.Exceptions.exceptions("MasterFileHandler", ex);
        }
        map.remove(token);
        fileHandler.ReadWrite.writeObject(new File("MasterFile.sp"), password, map);
        if (signal)
            password = "";
    }
    static Profile getUser ( Token token, boolean signal ) {
        TreeMap<Token, Profile> map = new TreeMap<>(new Token(0));
        try {
            map = ( TreeMap<Token, Profile> ) fileHandler.ReadWrite.readObject(new File("MasterFile.sp"), password);
        } catch (ClassNotFoundException ex) {
            fileHandler.Exceptions.exceptions("MasterFileHandler", ex);
        }
        Profile user = map.get(token);
        fileHandler.ReadWrite.writeObject(new File("MasterFile.sp"), password, map);
        if (signal)
            password = "";
        return user;
    }
    static boolean getStatus () {
        if ( status == 0 )
            return false;
        else
            return true;
    }
}