package rpg_tests;

import interfaces.Target;
import interfaces.Weapon;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;

public class HeroTests {

    private static final int ATTACK_POINTS = 10;
    private static final int DURABILITY = 10;
    private static final int XP = 10;
    private static final String HERO_NAME = "Pesho";

    @Test
    public void attackGiveXPIfTargetIsDead(){

        Weapon weapon = Mockito.mock(Weapon.class);
        Target target = Mockito.mock(Target.class);

        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveExperience()).thenReturn(XP);


        Hero hero = new Hero(HERO_NAME,weapon);

        hero.attack(target);

        Assert.assertEquals("Wrong XP",XP,hero.getExperience());



    }



}
