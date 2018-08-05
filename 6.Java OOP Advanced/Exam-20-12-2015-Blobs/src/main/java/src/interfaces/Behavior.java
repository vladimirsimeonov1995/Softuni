package src.interfaces;

import src.models.Blob;

public interface Behavior {

    boolean isTriggered();

    void trigger(Blob source);

    void applyRecurrentEffect(Blob source);
}
