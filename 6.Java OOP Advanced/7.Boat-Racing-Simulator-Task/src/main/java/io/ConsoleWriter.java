package io;

import contracts.Writer;

public class ConsoleWriter implements Writer {

    public ConsoleWriter() {
    }

    public void writeLine(String text){
        System.out.println(text);
    }

    public void write(String text){
        System.out.print(text);
    }

}
