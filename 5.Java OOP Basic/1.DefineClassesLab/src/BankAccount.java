public class BankAccount {

    private static final double INTEREST_RATE_DEFAULT = 0.02;

    private static int startCount = 1;
    private static double interestRate = INTEREST_RATE_DEFAULT;

    private int id;
    private double balance;

    public BankAccount(){
        this.id = startCount++;
    }

    public int getId(){
        return this.id;
    }

    public static void setInterestRate(double interest){
        interestRate = interest;
    }

    public double getInterest(int years){
        return balance * interestRate * years;
    }

    public void deposit(double amount){
        this.balance += amount;
    }




}
