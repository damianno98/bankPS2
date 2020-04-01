import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankImpl implements Bank {
    private List<Account> accounts;
    private final static Logger log = Logger.getLogger(BankImpl.class.getName());
    public BankImpl(){
        accounts = new LinkedList<>();
        log.info("New bank instance created!");
    }

    @Override
    public Long createAccount(String name, String address) {
        log.fine("Creating account...");
        if(name.length() < 2 || address.length() < 2)
            throw new IllegalArgumentException("Wrong name or address!");
        Account newAccount = new Account((long) (accounts.size() + 1), name, address);
        for(Account account : accounts){
            if(account.equals(newAccount)) {
                log.fine("Created account id: " + account.getId());
                return account.getId();
            }
        }
        accounts.add(newAccount);
        log.fine("Created account id: " + newAccount.getId());
        return newAccount.getId();
    }

    @Override
    public Long findAccount(String name, String address) {
        log.fine("Finding account...");
        Account tempAccount = new Account(name, address);
        for(Account account : accounts){
            if(account.equals(tempAccount)) {
                log.fine("Found account id: " + account.getId());
                return account.getId();
            }
        }
        throw new AccountIdException("There is no account with specified id!");
    }

    @Override
    public void deposit(Long id, BigDecimal amount) {
        log.fine("Deposit started...");
        Account account = getAccountById(id);
        if(account == null )
            throw new AccountIdException("There is no account with specified id!");
        else account.setBalance(account.getBalance().add(amount));
        log.fine("Deposit completed + " + amount);
    }

    @Override
    public BigDecimal getBalance(Long id) {
        log.fine("Checking balance...");
        Account account = getAccountById(id);
        if(account == null )
            throw new AccountIdException("There is no account with specified id!");
        else {
            log.fine("Balance checked!");
            return account.getBalance();
        }
    }

    @Override
    public void withdraw(Long id, BigDecimal amount) {
        log.fine("Withdraw started...");
        Account account = getAccountById(id);
        if(account != null ) {
            if (account.getBalance().compareTo(amount) < 0)
                throw new InsufficientFundsException("There is not enough money!");
            else {
                account.setBalance(account.getBalance().subtract(amount));
                log.fine("Withdraw completed!");
            }
        }
        else
            throw new AccountIdException("There is no account with specified id!");
    }

    @Override
    public void transfer(Long idSource, Long idDestination, BigDecimal amount) {
        log.fine("Transfer started...");
        Account source = getAccountById(idSource);
        Account destination = getAccountById(idDestination);
        if(source == null || destination == null)
            throw new AccountIdException("There is no account with specified id!");
        if(source.getBalance().compareTo(amount) < 0)
            throw new InsufficientFundsException("There is not enough money!");
        else{
            source.setBalance(source.getBalance().subtract(amount));
            destination.setBalance(destination.getBalance().add(amount));
            log.fine("Transfer completed!");
        }
    }

    private Account getAccountById(Long id){
        for(Account account : accounts){
            if(account.getId().equals(id))
                return account;
        }
        return null;
    }
}
