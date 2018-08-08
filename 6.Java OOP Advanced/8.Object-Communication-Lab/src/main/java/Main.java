import interfaces.*;
import models.Dragon;
import models.Warrior;
import models.commands.*;
import models.groups.Group;
import models.loggers.CombatLogger;
import models.loggers.ErrorLogger;
import models.loggers.EventLogger;
import models.loggers.TargetLogger;

public class Main {

    public static void main(String[] args) {

        Handler combatLogger = makeHandlers();

        Attacker attacker = new Warrior("Pesho",20,combatLogger);
        Attacker attacker1 = new Warrior("Vasko",20,combatLogger);
        Attacker attacker2 = new Warrior("Vladi",20,combatLogger);
        Attacker attacker3 = new Warrior("Kiro",20,combatLogger);
        Target dragon = new Dragon("Gosho Drakona", 80, 20, combatLogger);

        AttackGroup group = new Group();
        group.addMember(attacker);
        group.addMember(attacker1);
        group.addMember(attacker2);
        group.addMember(attacker3);




        //commandForSingleAttack(attacker, dragon);

        //groupAttack(dragon, group);





    }

    private static void groupAttack(Target dragon, AttackGroup group) {
        Command groupTargetCommand = new GroupTargetCommand(group,dragon);
        Command groupAttackCommand = new GroupAttackCommand(group);

        Executor commandExecutor = new CommandExecutor();

        commandExecutor.executeCommand(groupTargetCommand);
        commandExecutor.executeCommand(groupAttackCommand);
    }

    private static void commandForSingleAttack(Attacker attacker, Target dragon) {
        Command targetCommand = new TargetCommand(attacker,dragon);
        Command attackCommand = new AttackCommand(attacker);

        CommandExecutor commandExecutor = new CommandExecutor();

        commandExecutor.executeCommand(targetCommand);
        commandExecutor.executeCommand(attackCommand);
    }

    private static Handler makeHandlers() {
        Handler combatLogger = new CombatLogger();
        Handler eventLogger = new EventLogger();
        Handler errorLogger = new ErrorLogger();

        combatLogger.setSuccessor(eventLogger);
        eventLogger.setSuccessor(errorLogger);
        errorLogger.setSuccessor(new TargetLogger());
        return combatLogger;
    }


}
