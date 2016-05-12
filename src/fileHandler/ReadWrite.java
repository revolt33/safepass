package fileHandler;

import java.io.*;
import startUp.Check;

public class ReadWrite {
    public static void createEmptyFile(File file, String key) {
        byte[] status = {7,7,7};
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(status);
            fos.close();
            CryptoUtils.encrypt(key, file, file);
        } catch (FileNotFoundException ex) {
            fileHandler.Exceptions.exceptions("ReadWrite", ex);
        } catch (IOException | CryptoException ex) {
            fileHandler.Exceptions.exceptions("ReadWrite", ex);
        }
    }
    public static  void writeObject (File file, String key, Object object) {
        byte[] status = {7,7,5};
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(status);
            WriteObject write = new WriteObject(fos);
            write.writeObject(object);
            fos.close();
            CryptoUtils.encrypt(key, file, file);
        } catch (FileNotFoundException ex) {
            fileHandler.Exceptions.exceptions("ReadWrite", ex);
        } catch (IOException | CryptoException ex) {
            fileHandler.Exceptions.exceptions("ReadWrite", ex);
        }
    }
    public static Object readObject (File file, String key) throws ClassNotFoundException {
        Object object = null;
        if (Check.checkForFiles(file, key) == 2) {
            File temp = new File("temp");
            try {
                CryptoUtils.decrypt(key, file, temp);
                FileInputStream fis = new FileInputStream(temp);
                byte[] status = new byte[3];
                fis.read(status);
                object = new ReadObject(fis).readObject();
                fis.close();
            } catch (    CryptoException | FileNotFoundException ex) {
                fileHandler.Exceptions.exceptions("ReadWrite", ex);
            } catch (IOException ex) {
                fileHandler.Exceptions.exceptions("ReadWrite", ex);
            }
            temp.delete();
        }
        return object;
    }
}
