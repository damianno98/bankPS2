import java.math.BigDecimal;

public interface Bank {
    /**
     * Tworzy nowe lub zwraca id istniejącego konta.
     *
     * @param name imie i nazwisko własciciela
     * @param address adres własciciela
     * @return id utworzonego lub istniejacego konta.
     */
    Long createAccount(String name, String address);

    /**
     * Znajduje identyfikator konta.
     *
     * @param name imię i nazwisko właściciela
     * @param address adres właściciela
     * @return id konta lub null, gdy brak konta o podanych parametrach
     */
    Long findAccount(String name, String address);

    /**
     * Dodaje srodki do konta.
     *
     * @param id id
     * @param amount srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     */
    void deposit(Long id, BigDecimal amount);

    /**
     * Zwraca ilosc srodkow na koncie.
     *
     * @param id id
     * @return srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     */
    BigDecimal getBalance(Long id);

    /**
     * Pobiera srodki z konta.
     *
     * @param id id
     * @param amount srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     * @throws InsufficientFundsException gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    void withdraw(Long id, BigDecimal amount);

    /**
     * Przelewa srodki miedzy kontami.
     *
     * @param idSource idSource
     * @param idDestination idDestination
     * @param amount srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     * @throws InsufficientFundsException gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    void transfer(Long idSource, Long idDestination, BigDecimal amount);

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message){
            super(message);
        }
    };

    class AccountIdException extends RuntimeException {
        public AccountIdException(String message){
            super(message);
        }
    };
}