package banking_system.model;

import jakarta.persistence.*;
/*
   Entidade de cliente mapeada para o banco de dados/
   Armazena informações usadas para identificação e autenticação
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Email único usado para identificação no sistema
    @Column(unique = true, nullable = false) 
    private String email;
    private String password;

    public Customer() {}
    
     // Cria um cliente com os dados básicos
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
