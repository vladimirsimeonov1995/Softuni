package app.factories;


import app.entities.powers.Power;
import app.interfaces.comicWarInterfaces.SuperPower;
import app.interfaces.io_interfaces.OutputWriter;
import app.io.ConsoleWriter;

public class SuperPowerFactory {

    private OutputWriter writer;

    public SuperPowerFactory(){
        this.writer = new ConsoleWriter();
    }

    public SuperPower createSuperPower(String name,double points){
        try {
            return new Power(name,points);
        }catch (IllegalArgumentException ex){
            writer.writeNewLine(ex.getMessage());
        }

        return null;
    }

}
