package data;

import java.io.File;
import java.io.Serializable;
import java.util.TreeMap;

public final class Userdata implements Serializable{
    public static int userCount;
    public static boolean status;
    public static TreeMap<Token, Profile> users = new TreeMap<>(new Token(0));
    private static Profile user;
    public static void setUser ( Profile user ) {
        Userdata.user = user;
    }
    public static Profile getUser () {
        return user;
    }
    public static void updateUser ( int count ) {
        user.setAccounts(count);
        Profile temp = users.remove(new Token(user.getSerial()));
        temp.setAccounts(user.getAccounts());
        users.put(new Token(temp.getSerial()), temp);
        fileHandler.ReadWrite.writeObject(new File("User"), "insecureinsecure", users);
    }
}
