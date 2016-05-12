package fileHandler;

import java.io.*;

public class Exceptions {
    public static void exceptions(String dest, Throwable ex) {
        PrintWriter pw = null;
        try {
            File file = new File("Errors");
            if ( !file.isDirectory() )
                file.mkdir();
            pw = new PrintWriter(new FileOutputStream("Errors"+ File.separator + dest, true));
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace(pw);
        }
        ex.printStackTrace(pw);
        pw.flush();
        pw.close();
    }
}
