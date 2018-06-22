package P08_PokemonTrainer;

import java.util.Map;

public class Trainer {

    private String name;
    private int numberOfBadges;
    private Map<Pokemon ,String > pokemons;


    public Trainer(String name, Map<Pokemon, String> pokemons) {
        this.name = name;
        this.numberOfBadges = 0;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfBadges() {
        return numberOfBadges;
    }

    public Map<Pokemon, String> getPokemons() {
        return pokemons;
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.put(pokemon,pokemon.getElement());
    }

    public void checkElement(String element){

        if(this.pokemons.containsValue(element))
            this.numberOfBadges++;
        else {
            for (Pokemon pokemon : pokemons.keySet()) {
                pokemon.pokemonHeathLower();
                if(pokemon.getHealth() <= 0)
                    pokemons.remove(pokemon);
            }
        }
    }
}
