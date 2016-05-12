package Listeners;

import data.Carrier;
import data.Profile;
import data.Userdata;
import fileHandler.WriteObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class ExportProfile implements ActionListener {

    JComboBox<Object> users;

    public ExportProfile(JComboBox users) {
        this.users = users;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( Userdata.users.size() > 0) {
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int select = choose.showOpenDialog(startUp.MainClass.window);
            if (select == JFileChooser.APPROVE_OPTION) {
                File file = choose.getSelectedFile();
                Profile user = (Profile) users.getSelectedItem();
                long size = user.getFile().length();
                int length = (int) size;
                byte[] array = new byte[length];
                try {
                    FileInputStream fis = new FileInputStream(user.getFile());
                    fis.read(array);
                    fis.close();
                } catch (FileNotFoundException ex) {
                    fileHandler.Exceptions.exceptions("ExportProfile", ex);
                } catch (IOException ex) {
                    fileHandler.Exceptions.exceptions("ExportProfile", ex);
                }
                user.setFPActive(false);
                user.setQuestion(0);
                try {
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + File.separator + user.getFile().getName()+".exp");
                    WriteObject obj = new WriteObject(fos);
                    obj.writeObject(new Carrier(user, array));
                    fos.close();
                } catch (FileNotFoundException ex) {
                    fileHandler.Exceptions.exceptions("ExportProfile", ex);
                } catch (IOException ex) {
                    fileHandler.Exceptions.exceptions("ExportProfile", ex);
                }
            }
        }
    }
}
