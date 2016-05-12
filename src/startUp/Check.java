package startUp;

import fileHandler.*;
import java.io.*;

public class Check {
    public static byte checkForFiles (File file, String key) {
        byte status = 3;
        if (file.isFile()) {
            FileInputStream fis = null;
            File temp = new File("temp");
            byte[] parity = new byte[3];
            try {
                CryptoUtils.decrypt(key, file, temp);
                fis = new FileInputStream(temp);
                fis.read(parity);
                fis.close();
                temp.delete();
            } catch (    IOException | CryptoException ex) {
                fileHandler.Exceptions.exceptions("Check", ex);
            }
            for (int i = 0; i < 2; i++) {
                if (parity[i] == 7)
                    status = 1;
                else {
                    status = 0;
                    break;
                }
            }
            if (parity[2] == 5 && status == 1)
                status = 2;
            else if (parity[2] == 7 && status == 1) 
                status = 1;
            else
                status = 0;
        }
        return status;
    }
}
