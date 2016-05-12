package data;

public class SecurityQuestion {
    private String question;
    private int serial;
    public SecurityQuestion ( String question, int serial ) {
        this.question = question;
        this.serial = serial;
    }
    @Override
    public String toString () {
        return question;
    }
    public int getSerial () {
        return serial;
    }
}
