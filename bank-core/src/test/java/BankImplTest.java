
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BankImplTest {
    Bank bank;
    private final static Logger log = Logger.getLogger(BankImplTest.class.getName());
    @BeforeEach
    void setUp() {
        bank = new BankImpl();
    }

    @Test
    void createAccount() {
        Assertions.assertNotNull(bank.createAccount("Damian Półkośnik", "Białystok"));
    }

    @Test
    void createAccountWithWrongArgument(){
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> bank.createAccount("","asda")
        );
        Assertions.assertEquals("Wrong name or address!", exception.getMessage());
    }

    @Test
    void findAccount() {
        Long id = bank.createAccount("Damian Półkośnik", "Białystok");
        Assertions.assertEquals(id, bank.findAccount("Damian Półkośnik", "Białystok"));
    }

    @Test
    void deposit() {
        BigDecimal expectedAmount = BigDecimal.valueOf(1500);
        Long id = bank.createAccount("Damian Półkośnik", "Białystok");
        bank.deposit(id, expectedAmount);
        Assertions.assertEquals(expectedAmount, bank.getBalance(id));
    }

    @Test
    void getBalance() {
        Long id = bank.createAccount("Damian Półkośnik", "Białystok");
        Assertions.assertEquals(BigDecimal.valueOf(0), bank.getBalance(id));
        bank.deposit(id, BigDecimal.valueOf(1000));
        Assertions.assertEquals(BigDecimal.valueOf(1000), bank.getBalance(id));
    }

    @Test
    void withdraw() {
        Long id = bank.createAccount("Damian Półkośnik", "Białystok");
        bank.deposit(id, BigDecimal.valueOf(1000));
        bank.withdraw(id, BigDecimal.valueOf(500.50));
        Assertions.assertEquals(BigDecimal.valueOf(499.50), bank.getBalance(id));
    }

    @Test
    void transfer() {
        long idSource = bank.createAccount("Damian Półkośnik2", "Białystok");
        long idDestination = bank.createAccount("Damian Półkośnik3", "Białystok");
        bank.deposit(idSource, BigDecimal.valueOf(1000));
        bank.transfer(idSource, idDestination, BigDecimal.valueOf(500));
        Assertions.assertEquals(BigDecimal.valueOf(500), bank.getBalance(idSource));
        Assertions.assertEquals(BigDecimal.valueOf(500), bank.getBalance(idDestination));
    }
}