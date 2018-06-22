package P08_PokemonTrainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String ,Trainer> trainers = new LinkedHashMap<>();

        getTrainers(reader, trainers);

        tournament(reader, trainers);

        trainers.entrySet().stream()
                .sorted((trainer1,trainer2) -> Integer.compare(trainer2.getValue().getNumberOfBadges()
                        ,trainer1.getValue().getNumberOfBadges()))
                .forEach(trainer -> {
                    System.out.printf("%s %d %d\n",
                            trainer.getValue().getName(),
                            trainer.getValue().getNumberOfBadges(),
                            trainer.getValue().getPokemons().size());
                });


    }

    private static void tournament(BufferedReader reader, Map<String, Trainer> trainers) throws IOException {
        while (true){

            String element = reader.readLine();
            if("End".equals(element))
                break;

            for (Trainer trainer : trainers.values()) {
                trainer.checkElement(element);
            }

        }
    }

    private static void getTrainers(BufferedReader reader, Map<String, Trainer> trainers) throws IOException {
        while (true){

            String[] eachLine = reader.readLine().split(" ");
            if("Tournament".equals(eachLine[0]))
                break;

            String trainerName = eachLine[0];
            String pokemonName = eachLine[1];
            String pokemonElement = eachLine[2];
            int pokemonHealth = Integer.parseInt(eachLine[3]);

            if (!trainers.containsKey(trainerName)){

                Map<Pokemon,String> currentTrainerPokemons = new LinkedHashMap<>();
                currentTrainerPokemons
                        .put(new Pokemon(pokemonName,pokemonElement,pokemonHealth),pokemonElement);
                trainers.put(trainerName,new Trainer(trainerName,currentTrainerPokemons));

            }else{

                trainers.get(trainerName)
                        .addPokemon(new Pokemon(pokemonName,pokemonElement,pokemonHealth));

            }
        }
    }


}
