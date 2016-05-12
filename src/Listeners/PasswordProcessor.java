package Listeners;

public class PasswordProcessor {
    static String passwordBuilder (String pass) {
        int loop, rem = pass.length()%16;
        loop = 16 - rem;
        String password = pass;
        for ( int i = 0; i <loop; i++) {
            password = password + pass.charAt(i);
        }
        return password;
    }
}
