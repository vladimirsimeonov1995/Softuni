package rpg_tests;

import interfaces.Target;
import interfaces.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class DummyTests {


    private static final int ZERO = 0;
    private static final int FIVE = 5;
    private static final int TEN = 10;

    private Weapon axe;
    private Target dummy;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(TEN, TEN);
        this.dummy = new Dummy(TEN, TEN);
    }

    @Test
    public void dummyLosesHealthAfterTakeAttack(){

        this.axe = new Axe(FIVE,TEN);

        this.axe.attack(this.dummy);

        Assert.assertEquals("Not expected Dummy's health.",FIVE,this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void deadDummyThrowsExceptionIfAttacked(){

        this.dummy = new Dummy(ZERO,TEN);

        this.axe.attack(this.dummy);

    }

    @Test
    public void deadDummyCanGiveXP(){

        this.dummy = new Dummy(ZERO,TEN);

        Assert.assertEquals("Not expected XP.",TEN,this.dummy.giveExperience());

    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyCantGiveXP(){

        this.dummy.giveExperience();

    }

}
