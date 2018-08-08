package models.loggers;

import abstractions.Logger;
import enums.LogType;
import interfaces.Handler;

public class CombatLogger extends Logger {

    private Handler successor = new EventLogger();


    @Override
    public void handle(LogType type, String message) {

        if(type == LogType.ATTACK || type == LogType.MAGIC){
            System.out.println(type.name() + ":" + message);
        }

        super.passToSuccessor(type,message);

    }

    @Override
    public void setSuccessor(Handler successor) {
        super.setSuccessor(successor);
    }
}
