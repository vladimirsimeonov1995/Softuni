import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer,BankAccount> accounts = new HashMap<>();

        while (true){
            String[] arguments = reader.readLine().split(" ");
            if("End".equals(arguments[0]))
                break;


            int currentId ;

            switch (arguments[0]){
                case "Create":
                    BankAccount newAccount = new BankAccount();
                    accounts.put(newAccount.getId(),newAccount);
                    System.out.printf("Account ID%d created\n",newAccount.getId());
                    break;
                case "Deposit":
                    currentId = Integer.parseInt(arguments[1]);
                    if(!accounts.containsKey(currentId)){
                        System.out.print("Account does not exist\n");
                    }else {
                        double depositMoney = Double.parseDouble(arguments[2]);
                        accounts.get(currentId).deposit(depositMoney);
                        System.out.printf("Deposited %d to ID%d\n",(int)depositMoney,currentId);
                    }
                    break;
                case "SetInterest":
                    int interest = Integer.parseInt(arguments[1]);
                    BankAccount.setInterestRate(interest);
                    break;
                case "GetInterest":
                    currentId = Integer.parseInt(arguments[1]);
                    if(!accounts.containsKey(currentId))
                        System.out.print("Account does not exist\n");
                    else {
                        int years = Integer.parseInt(arguments[2]);
                        System.out.printf("%.2f\n",accounts.get(currentId).getInterest(years));
                    }
                    break;
            }

        }
    }

}
