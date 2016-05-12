package fileHandler;

import java.io.*;

public class WriteObject extends ObjectOutputStream{
    public WriteObject (OutputStream o) throws IOException{
        super(o);
    }
    @Override
    public void writeStreamHeader()
    {
        
    }
}