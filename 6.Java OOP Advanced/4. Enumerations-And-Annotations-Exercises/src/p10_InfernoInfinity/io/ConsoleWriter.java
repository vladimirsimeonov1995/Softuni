package p10_InfernoInfinity.io;

import p10_InfernoInfinity.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {


    @Override
    public void writeLine(String text) {
        System.out.print(text);
    }

    @Override
    public void writeNewLine(String text) {
        System.out.println(text);
    }
}
