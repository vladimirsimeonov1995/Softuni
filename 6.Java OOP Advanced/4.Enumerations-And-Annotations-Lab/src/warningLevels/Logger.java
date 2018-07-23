package warningLevels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

public class Logger {

    private List<Message> messages;
    private Importance importance;

    public Logger(String importance){
        this.importance = Enum.valueOf(Importance.class,importance);
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message){

        if(message.getImportance().ordinal() >= this.importance.ordinal()){
            this.messages.add(message);
        }

    }

    public Iterable<Message> getMessages(){
        return Collections.unmodifiableCollection(messages);
    }

}
