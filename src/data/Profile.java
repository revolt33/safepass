package data;

import java.io.*;
import java.util.*;

public final class Profile implements Serializable, Comparator{
    private String name, answer, key;
    private boolean fpactive;
    private int question, serial, accounts = 0;
    private File file;
    public Profile (String name, File file, int serial) {
        this.serial = serial;
        this.name = name;
        this.file = file;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setAnswer (String answer) {
        this.answer = answer;
    }
    public void setKey (String key) {
        this.key = key;
    }
    public void setFPActive (boolean fpactive) {
        this.fpactive = fpactive;
    }
    public void setQuestion (int question) {
        this.question = question;
    }
    public void setFile (File file) {
        this.file = file;
    }
    public String getName () {
        return name;
    }
    public String getAnswer () {
        return answer;
    }
    public String getKey () {
        return key;
    }
    public boolean getFPActive () {
        return fpactive;
    }
    public int getQuestion () {
        return question;
    }
    public void setSerial (int serial) {
        this.serial = serial;
    }
    public int getSerial () {
        return serial;
    }
    public File getFile () {
        return file;
    }
    public void setAccounts (int accounts) {
        this.accounts = accounts;
    }
    public int getAccounts () {
        return accounts;
    }
    @Override
    public String toString () {
        return name + " ("+ serial  +")";
    }
    @Override
    public int compare (Object newToken, Object oldToken) {
        return ( ((Profile)oldToken).serial-((Profile)newToken).serial);
    }
}