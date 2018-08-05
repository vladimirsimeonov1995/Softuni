package src.models.behavors;

import src.models.Blob;

public class Inflated extends AbstractBehavior {

    private static final int INFLATED_HEALTH_INCREASE = 50;
    private static final int INFLATED_HEALTH_DECREASE = 10;


    public Inflated() {
        super();
    }

    public void trigger(Blob source) {
        super.setIsTriggered(true);
        this.applyTriggerEffect(source);
    }


    public void applyRecurrentEffect(Blob source) {
        if (super.toDelayRecurrentEffect()) {
            super.setToDelayRecurrentEffect(false);
        } else {
            source.setHealth(source.getHealth() - INFLATED_HEALTH_DECREASE);
        }
    }

    private void applyTriggerEffect(Blob source) {
        source.setHealth(source.getHealth() + INFLATED_HEALTH_INCREASE);
    }

}
