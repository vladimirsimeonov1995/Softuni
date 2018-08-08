package models.commands;

import interfaces.AttackGroup;
import interfaces.Command;
import interfaces.Target;

public class GroupTargetCommand implements Command {

    private AttackGroup attackGroup;
    private Target target;

    public GroupTargetCommand(AttackGroup attackGroup, Target target) {
        this.attackGroup = attackGroup;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attackGroup.groupTarget(target);
    }
}
