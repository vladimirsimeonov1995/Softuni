package eventImplementation.interfaces;

import eventImplementation.implementations.NameChange;

public interface NameChangeListener {

    void handleChangedName(NameChange event);

}
