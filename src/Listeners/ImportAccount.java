package Listeners;

import data.Carrier;
import data.Profile;
import data.Token;
import data.Userdata;
import fileHandler.ReadObject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportAccount extends MouseAdapter{
    @Override
    public void mouseClicked ( MouseEvent me ) {
        JFileChooser choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        choose.addChoosableFileFilter(new FileNameExtensionFilter(null, ".exp"));
        int select = choose.showOpenDialog(startUp.MainClass.window);
        if ( select == JFileChooser.APPROVE_OPTION ) {
            try {
                FileInputStream fis = new FileInputStream(choose.getSelectedFile());
                ReadObject object = new ReadObject(fis);
                Carrier carrier = (Carrier) object.readObject();
                Userdata.userCount++;
                File file = new File("Account"+File.separator+"user"+Userdata.userCount);
                Profile user = carrier.getProfile();
                user.setFile(file);
                user.setSerial(Userdata.userCount);
                if ( Userdata.users == null )
                    Userdata.users = new TreeMap<>(new Token(0));
                Userdata.users.put(new Token(Userdata.userCount), user);
                fileHandler.ReadWrite.writeObject(new File("User"), "insecureinsecure", Userdata.users);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(carrier.getFile());
                fos.close();
                graphics.User.updateUsers();
            } catch (FileNotFoundException ex) {
                fileHandler.Exceptions.exceptions("ImportAccount", ex);
            } catch (    IOException | ClassNotFoundException ex) {
                fileHandler.Exceptions.exceptions("ImportAccount", ex);
            }
        }
    }
}
