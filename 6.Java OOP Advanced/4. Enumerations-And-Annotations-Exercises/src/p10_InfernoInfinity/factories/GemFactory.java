package p10_InfernoInfinity.factories;

import p10_InfernoInfinity.entities.gems.Amethyst;
import p10_InfernoInfinity.entities.gems.Emerald;
import p10_InfernoInfinity.entities.gems.Gem;
import p10_InfernoInfinity.entities.gems.Ruby;

public class GemFactory {

    public GemFactory(){}

    public Gem createGem(String type){

        switch (type){

            case "RUBY":
                return new Ruby();

            case "EMERALD":
                return new Emerald();

            case "AMETHYST":
                return new Amethyst();

                default: return null;

        }

    }

}
