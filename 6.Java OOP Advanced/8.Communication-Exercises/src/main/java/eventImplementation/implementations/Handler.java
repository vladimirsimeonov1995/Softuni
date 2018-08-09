package eventImplementation.implementations;

import eventImplementation.interfaces.NameChangeListener;

public class Handler implements NameChangeListener {

    public void handleChangedName(NameChange event) {
        System.out.printf("Dispatcher's name changed to %s.\n",event.getChangedName());
    }
}
