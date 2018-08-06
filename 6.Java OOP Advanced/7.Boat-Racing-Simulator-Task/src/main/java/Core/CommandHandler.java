package Core;

import Utility.Constants;
import contracts.IBoatSimulatorController;
import contracts.ICommandHandler;
import controllers.BoatSimulatorController;
import enumeration.EngineType;
import exeptions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CommandHandler implements ICommandHandler {

    private IBoatSimulatorController controller;

    public CommandHandler(IBoatSimulatorController controller) {
        this.setController(controller);
    }


    public IBoatSimulatorController getController() {
        return this.controller;
    }

    private void setController(IBoatSimulatorController controller) {
        this.controller = controller;
    }

    public String ExecuteCommand(String name, List<String> parameters) throws
            NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Method method = this.getController().getClass().getDeclaredMethod(name,List.class);

        method.setAccessible(true);

        String result = (String) method.invoke(this.getController(),parameters);

        return result;


//        switch (name) {
//
//            case "GetStatistic":
//                return this.getController().GetStatistic();
//            default:
//                throw new IllegalArgumentException();
//        }
    }
}
