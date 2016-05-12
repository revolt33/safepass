package data;

import java.io.Serializable;
import java.util.Comparator;

public final class Account implements Serializable, Comparator{
    private String account, password;
    private int serial;
    public Account (String account, String password) {
        setAccount(account);
        setPassword(password);
    }
    public void setAccount (String account) {
        this.account = account;
    }
    public void setPassword (String password) {
        this.password = password;
    }
    public String getAccount () {
        return account;
    }
    public String getPassword () {
        return password;
    }
    public void setSerial (int serial ) {
        this.serial = serial;
    }
    public int getSerial () {
        return serial;
    }
    @Override
    public String toString () {
        return account;
    }
    @Override
    public int compare (Object newToken, Object oldToken) {
        return ( ((Account)oldToken).serial-((Account)newToken).serial);
    }
}
