package softuni.advancedquerylab.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Configurations {

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}
