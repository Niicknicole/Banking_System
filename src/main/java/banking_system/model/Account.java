package banking_system.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
/*
   Entidade de conta mapeada para o banco de dados
   Contém os dados básicos da conta e a relação com o cliente
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String branch;
    private BigDecimal balance = BigDecimal.ZERO;

    // Conta associada ao cliente
    @ManyToOne
    @JoinColumn(name = "customer_id")

    private Customer customer;
    public Account() {}

    public Account(Customer customer, String accountNumber, String branch) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.balance = BigDecimal.ZERO;
    }

    public Long getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public String getBranch() { return branch; }
    public BigDecimal getBalance() { return balance; }
    public Customer getCustomer() { return customer; }

     // Permite atualizar a agência
    public void setBranch(String branch) { this.branch = branch; }
     
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
