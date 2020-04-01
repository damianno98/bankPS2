import java.math.BigDecimal;

public class Account {
    private Long id;
    private String name;
    private String address;
    private BigDecimal balance;

    public Account(String name, String address){
        this.name = name;
        this.address = address;
    }

    public Account(Long id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean equals(Account account){
        return this.name.equals(account.name) && this.address.equals(account.address);
    }
}
