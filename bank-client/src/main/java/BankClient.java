import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BankClient {
    private static Bank bank;
    private final static Logger log = Logger.getLogger(BankClient.class.getName());
    static void start(){
        int menu = 0;
        String name, address;
        BigDecimal ammount;
        Long id;
        Scanner sc = new Scanner(System.in);
        bank = new BankImpl();
        while(menu != 7){
            switch(menu){
                case 0:
                    System.out.println("1. Create account \n2. Find Account \n3.Get balance \n4.Deposit \n5.Withdraw \n6.Transfer \n7.Exit \n");
                    menu = sc.nextInt();
                    break;
                case 1:
                    System.out.println("\nEnter the name\n");
                    name = sc.next();
                    System.out.println("\nEnter the address\n");
                    address = sc.next();

                    try {
                        bank.createAccount(name, address);
                    }
                    catch (Exception e){
                        log.log(Level.SEVERE, e.getMessage() , e);
                    }
                    menu = 0;
                    break;
                case 2:
                    System.out.println("\nEnter the name\n");
                    name = sc.next();
                    System.out.println("\nEnter the address\n");
                    address = sc.next();
                    try{
                        bank.findAccount(name, address);
                    }
                    catch (Exception e){
                        log.log(Level.SEVERE, e.getMessage() , e);
                    }
                    menu = 0;
                    break;
                case 3:
                    System.out.println("\nEnter the id\n");
                    id = sc.nextLong();
                    try{
                        BigDecimal balance = bank.getBalance(id);
                        System.out.println(balance);
                    }
                    catch (Exception e){
                        log.log(Level.SEVERE, e.getMessage() , e);
                    }
                    menu = 0;
                    break;
                case 4:
                    System.out.println("\nEnter the id\n");
                    id = sc.nextLong();
                    System.out.println("\nHow much?\n");
                    ammount = sc.nextBigDecimal();
                    try{
                        bank.deposit(id, ammount);
                    }
                    catch (Exception e){
                        log.log(Level.SEVERE, e.getMessage() , e);
                    }
                    menu = 0;
                    break;
                case 5:
                    System.out.println("\nEnter the id\n");
                    id = sc.nextLong();
                    System.out.println("\nHow much?\n");
                    ammount = sc.nextBigDecimal();
                    try{
                        bank.withdraw(id, ammount);
                    }
                    catch (Exception e){
                        log.log(Level.SEVERE, e.getMessage() , e);
                    }
                    menu = 0;
                    break;
                case 6:
                    System.out.println("\nEnter the id source\n");
                    id = sc.nextLong();
                    long idDestination;
                    System.out.println("\nEnter the id destination\n");
                    idDestination = sc.nextLong();
                    System.out.println("\nHow much?\n");
                    ammount = sc.nextBigDecimal();
                    try{
                        bank.transfer(id, idDestination, ammount);
                    }
                    catch (Exception e){
                        log.log(Level.SEVERE, e.getMessage() , e);
                    }
                    menu = 0;
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(BankClient.class.getResource("/logging.properties"));
        //LogManager.getLogManager().readConfiguration(BankClient.class.getResourceAsStream("logging.properties"));
        start();
    }
}
