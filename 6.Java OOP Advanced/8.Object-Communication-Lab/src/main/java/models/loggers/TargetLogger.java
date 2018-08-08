package models.loggers;

import abstractions.Logger;
import enums.LogType;

public class TargetLogger extends Logger {
    @Override
    public void handle(LogType type, String message) {
        if(type == LogType.TARGET){
            System.out.println(type + ":" + message);
        }

        this.passToSuccessor(type,message);
    }
}
