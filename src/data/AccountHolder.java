package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AccountHolder {
    private static TreeMap<Token, Account> accounts = new TreeMap<>(new Token(0));
    public static void addAccount (Account account) {
        accounts.put(new Token(account.getSerial()), account);
        Userdata.updateUser(accounts.size());
        sort();
        graphics.Account.updateAccounts();
        storeTransaction();
    }
    public static void setAccountList ( TreeMap<Token, Account> accounts) {
        AccountHolder.accounts = accounts;
    }
    public static TreeMap<Token, Account> getAccountList () {
        return accounts;
    }
    public static data.Account getAccount (int serial ) {
        TreeMap<Token, Account> temp = new TreeMap<>(new Token(0));
        data.Account acc = null;
        try {
            temp = (TreeMap<Token, Account>) fileHandler.ReadWrite.readObject(Userdata.getUser().getFile(), Userdata.getUser().getKey());
            acc = temp.get(new Token(serial));
        } catch (ClassNotFoundException ex) {
        }
        return acc;
    }
    public static void updateAccount ( Account account ) {
        accounts.remove(new Token(account.getSerial()));
        accounts.put(new Token(account.getSerial()), account);
        graphics.Account.updateAccounts();
        storeTransaction();
    }
    public static void removeAccount ( Token token ) {
        accounts.remove(token);
        graphics.Account.updateAccounts();
        Userdata.updateUser(accounts.size());
        sort();
        storeTransaction();
    }
    private static void storeTransaction () {
        fileHandler.ReadWrite.writeObject(Userdata.getUser().getFile(), Userdata.getUser().getKey(), accounts);
    }
    private static void sort () {
        ArrayList list = new ArrayList();
        Set set = accounts.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<data.Token, data.Account> entry = (Map.Entry<data.Token, data.Account>) it.next();
            list.add(entry.getValue());
        }
        int count = 1;
        accounts.clear();
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            data.Account account = (Account) iterator.next();
            account.setSerial(count);
            accounts.put(new Token(count), account);
            count++;
        }
    }
}
