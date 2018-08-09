package eventImplementation.implementations;

import eventImplementation.interfaces.NameChangeListener;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private String name;
    private List<NameChangeListener> listeners;

    public Dispatcher(String name) {
        this.name = name;
        listeners = new ArrayList<NameChangeListener>();
    }

    public void addNameChangeListener(NameChangeListener listener){
        this.listeners.add(listener);
    }

    public void removeNameChangeListener(NameChangeListener listener){
        this.listeners.remove(listener);
    }

    public void fireNameChangeEvent(NameChange event){
        this.listeners.forEach(l -> l.handleChangedName(event));
    }
}
