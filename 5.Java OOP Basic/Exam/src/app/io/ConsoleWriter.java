package app.io;

import app.interfaces.io_interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {


    @Override
    public void writeLine(String string) {
        System.out.print(string);
    }

    public void writeNewLine(String string){
        System.out.println(string);
    }
}
