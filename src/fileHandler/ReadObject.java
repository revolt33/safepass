package fileHandler;

import java.io.*;

public class ReadObject extends ObjectInputStream{
    public ReadObject (InputStream i) throws IOException {
        super(i);
    }
    @Override
    public void readStreamHeader() throws  IOException, StreamCorruptedException
    {
        
    }
}