package Listeners;

import data.Userdata;
import java.awt.event.*;
import java.io.File;

public class Termination extends WindowAdapter{
    @Override
    public void windowClosing (WindowEvent we) {
        fileHandler.ReadWrite.writeObject(new File("Data"), "insecureinsecure", new data.Data(Userdata.userCount));
    }
}
