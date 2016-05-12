package Listeners;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class MasterPassword implements ActionListener{
    JPasswordField pass;
    JDialog dialog;

    MasterPassword(JPasswordField pass, JDialog dialog) {
        this.dialog = dialog;
        this.pass = pass;
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        String pass = String.valueOf(this.pass.getPassword());
        pass = PasswordProcessor.passwordBuilder(pass);
        File file = new File("MasterFile.sp");
        byte status = startUp.Check.checkForFiles(file, pass);
        switch (status) {
            case (byte) 0:
                MasterFileHandler.setPassword(null, status);
                JOptionPane.showMessageDialog(startUp.MainClass.window, "Incorrect Password!");
                break;
            case (byte) 1:
                MasterFileHandler.setPassword(pass, status);
                break;
            case (byte) 2:
                MasterFileHandler.setPassword(pass, status);
                break;
            case (byte) 3:
                fileHandler.ReadWrite.createEmptyFile(file, pass);
                MasterFileHandler.setPassword(pass, status);
                break;
        }
        dialog.dispose();
    }
}
