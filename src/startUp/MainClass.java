package startUp;
import Listeners.Termination;
import data.Userdata;
import data.Profile;
import data.Token;
import fileHandler.Exceptions;
import fileHandler.ReadWrite;
import graphics.MainWindow;
import java.io.File;
import java.util.TreeMap;
import javax.swing.*;

public class MainClass {
    public static MainWindow window;
    public static void main(String[] s){
        try {
            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Exceptions.exceptions("MainClass", ex);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        byte status = Check.checkForFiles(new File("User"), "insecureinsecure");
        switch (status) {
            case (byte) 0:
                break;
            case (byte) 1:
                Userdata.status = false;
                break;
            case (byte) 2:
                Userdata.status = true;
                try {
                    Userdata.users = (TreeMap<Token, Profile>) fileHandler.ReadWrite.readObject(new File("User"), "insecureinsecure");
                } catch (ClassNotFoundException ex) {
                    Exceptions.exceptions("MainClass", ex);
                }
                break;
            case (byte) 3:
                break;
        }
        if (status == 3)
            fileHandler.ReadWrite.createEmptyFile(new File("User"), "insecureinsecure");
        File account = new File("Account");
        if (!account.isDirectory())
            account.mkdir();
        File data = new File("Data");
        if (!data.isFile())
            ReadWrite.writeObject(data, "insecureinsecure", new data.Data(0));
        else
            try {
            Userdata.userCount = ((data.Data) ReadWrite.readObject(data, "insecureinsecure")).getUserCount();
        } catch (ClassNotFoundException ex) {
            fileHandler.Exceptions.exceptions("MainClass", ex);
        }
       window = new graphics.MainWindow();
       window.addWindowListener(new Termination());
    }
}