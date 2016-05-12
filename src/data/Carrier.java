package data;

import java.io.Serializable;

public final class Carrier implements Serializable{
    private byte[] file;
    private Profile user;
    public Carrier ( Profile user, byte[] file ) {
        this.file = file;
        this.user = user;
    }
    public void setProfile ( Profile user ) {
        this.user = user;
    }
    public void setFile ( byte[] file ) {
        this.file = file;
    }
    public Profile getProfile () {
        return user;
    }
    public byte[] getFile () {
        return file;
    }
}
