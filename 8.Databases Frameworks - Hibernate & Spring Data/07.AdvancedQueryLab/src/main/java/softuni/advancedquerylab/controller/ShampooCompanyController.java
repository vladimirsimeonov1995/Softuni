package softuni.advancedquerylab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import softuni.advancedquerylab.service.shampooService.ShampooService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class ShampooCompanyController implements CommandLineRunner {

    private final ShampooService shampooService;
    private final Scanner scanner;

    @Autowired
    public ShampooCompanyController(ShampooService shampooService, Scanner scanner) {
        this.shampooService = shampooService;
        this.scanner = scanner;
    }


    @Override
    public void run(String... args) throws Exception {


        //printShampooBySize();
        //printShampooBySizeOrLabelId();
        //printShampooByPriceAfter();
        //printIngredientByNameStartWith();
        //printIngredientsByNames();
        //printShampoosCountWithPriceLowerThen();
        //printShampooWithIngredientIn();
        //printShampooWithIngredientCountLessThen();
        //printIngredientsTotalPriceByShampooBrand();


    }

    private void printIngredientsTotalPriceByShampooBrand() {
        while (true){
            System.out.print("Please enter shampoo name(press enter to exit): ");

            String name = scanner.nextLine();
            if("".equals(name)){
                break;
            }

            Double price = shampooService.selectIngredientsPriceByShampooName(name);

            System.out.printf("%.2f\n", price);

        }
    }

    private void printShampooWithIngredientCountLessThen() {
        System.out.print("Please enter max count of ingredients for shampoo: ");
        String count = scanner.nextLine();

        shampooService.selectShampoosWithIngredientByCountLessThen(count)
                .forEach(System.out::println);
    }

    private void printShampooWithIngredientIn() {
        List<String> names = getInputList();

        shampooService.selectShampoosWithIngredientIn(names)
                .forEach(System.out::println);
    }

    private void printShampoosCountWithPriceLowerThen() {
        System.out.print("Enter price: ");

        String price = scanner.nextLine();

        Integer count = shampooService.getShampooCountWithPriceLowerThen(price);

        System.out.println(count);
    }

    private void printIngredientsByNames() {
        List<String> lines = getInputList();

        shampooService.selectIngredientsByNames(lines)
                .forEach(System.out::println);
    }

    private List<String> getInputList() {
        List<String> lines = new ArrayList<>();

        System.out.print("Please enter names and enter empty line for end: ");

        while (true) {
            String line = scanner.nextLine();

            if ("".equals(line)) {
                break;
            }

            lines.add(line);
        }
        return lines;
    }

    private void printIngredientByNameStartWith() {
        System.out.print("Please enter what name should start with: ");
        String nameStart = scanner.nextLine();

        shampooService.selectIngredientsByNameStartsWith(nameStart)
                .forEach(System.out::println);
    }

    private void printShampooBySize() {
        String size = scanner.nextLine();

        shampooService.selectShampoosBySize(size)
                .forEach(System.out::println);
    }

    private void printShampooBySizeOrLabelId() {
        System.out.print("Please enter size: ");
        String size = scanner.nextLine();

        System.out.print("Please enter Label id: ");
        String labelId = scanner.nextLine();

        shampooService.selectShampoosBySizeOrLabel(size, labelId)
                .forEach(System.out::println);
    }

    private void printShampooByPriceAfter() {
        System.out.print("Please enter price: ");
        String price = scanner.nextLine();

        shampooService.selectShampoosByPriceAfter(price)
                .forEach(System.out::println);
    }
}
