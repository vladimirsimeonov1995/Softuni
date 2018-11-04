package app.io;

import app.Interfaces.Writer;

public class ConsoleWriter implements Writer {

    public ConsoleWriter() {
    }

    public void writeLine(String line) {
        System.out.print(line);
    }

}
