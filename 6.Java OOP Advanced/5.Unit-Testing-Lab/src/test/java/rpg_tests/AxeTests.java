package rpg_tests;


import interfaces.Target;
import interfaces.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;


public class AxeTests {

    private static final int DUMMY_HEALTH_TEN = 10;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final int WEAPON_ATTACK_POINTS = 10;
    private static final int WEAPON_DURABILITY = 10;
    private static final int EXPECTED_DURABILITY = 9;
    private static final int ZERO = 0;

    private Weapon axe;
    private Target dummy;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(WEAPON_ATTACK_POINTS, WEAPON_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH_TEN, DUMMY_EXPERIENCE);
    }


    @Test
    public void weaponLosesDurabilityAfterAttack() {

        this.axe.attack(this.dummy);

        Assert.assertEquals("Non expected durability", EXPECTED_DURABILITY, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void attackWithBrokenWeapon() {
        this.axe = new Axe(WEAPON_ATTACK_POINTS, ZERO);


        this.axe.attack(this.dummy);
    }

}
