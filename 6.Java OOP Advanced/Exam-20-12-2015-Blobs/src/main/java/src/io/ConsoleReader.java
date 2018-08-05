package src.io;

import src.interfaces.io.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    BufferedReader bufferedReader ;

    public ConsoleReader(){
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine(){
        try {
            return bufferedReader.readLine();
        } catch (IOException ignore) {
           ;
        }

        return null;
    }

}
