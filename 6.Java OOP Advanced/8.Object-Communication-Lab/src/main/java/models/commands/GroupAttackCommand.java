package models.commands;

import interfaces.AttackGroup;
import interfaces.Command;

public class GroupAttackCommand implements Command {

    private AttackGroup attackGroup;

    public GroupAttackCommand(AttackGroup attackGroup) {
        this.attackGroup = attackGroup;
    }


    @Override
    public void execute() {
        this.attackGroup.groupAttack();
    }
}
