package io;

import contracts.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    private BufferedReader reader ;

    public ConsoleReader(){
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
