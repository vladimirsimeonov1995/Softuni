package src.io;

import src.interfaces.io.Writer;

public class ConsoleWriter implements Writer {

    public void whiteText(String text){
        System.out.print(text);
    }

    public void writeTextWithNewLine(String text){
        System.out.println(text);
    }

}
