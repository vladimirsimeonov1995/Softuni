package kingsGambit;

import kingsGambit.commands.AttackCommand;
import kingsGambit.commands.KillCommand;
import kingsGambit.interfaces.Command;
import kingsGambit.interfaces.Deadable;
import kingsGambit.persons.Footman;
import kingsGambit.persons.King;
import kingsGambit.persons.Person;
import kingsGambit.persons.RoyalGuard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> personList = new LinkedHashMap<>();

        getList(reader, personList);



        while (true){

            String[] command = reader.readLine().split(" ");

            if("End".equals(command[0]))
                break;

            if("Kill".equals(command[0])){
                Command killCommand = new KillCommand((Deadable) personList.get(command[1]));
                killCommand.execute();
            }else{

                Command attackCommand = new AttackCommand(personList);
                attackCommand.execute();
            }



        }


    }

    private static void getList(BufferedReader reader, Map<String,Person> personList) throws IOException {
        String kingsName = reader.readLine();

        personList.put(kingsName,new King(kingsName));

        String[] guardsNames = reader.readLine().split(" ");

        Arrays.stream(guardsNames).forEach(gn -> personList.put(gn,new RoyalGuard(gn)));

        String[] footmansNames = reader.readLine().split(" ");

        Arrays.stream(footmansNames).forEach(fm -> personList.put(fm,new Footman(fm)));
    }

}
