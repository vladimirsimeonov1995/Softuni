package models.loggers;

import abstractions.Logger;
import enums.LogType;

public class ErrorLogger extends Logger {


    @Override
    public void handle(LogType type, String message) {
        if(type == LogType.ERROR){
            System.out.println(type + ":" + message);
        }

        super.passToSuccessor(type,message);
    }
}
