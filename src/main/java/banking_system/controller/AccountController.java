package banking_system.controller;

import banking_system.dto.account.*;
import banking_system.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controller responsável por expor as operações da conta via API
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;
    // Injeção do service responsável pela lógica da conta
    public AccountController(AccountService service) {
        this.service = service;
    }

    //Criar uma nova conta para um cliente
    @PostMapping
    public ResponseEntity<AccountDetailsResponseDTO> create(@RequestBody AccountDetailsRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<AccountDetailsResponseDTO> getByCustomer(@PathVariable Long customerId) {
        var result = service.getAccountByCustomerId(customerId);
        return (result == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }
    //depósito
    @PostMapping("/deposit")
    public ResponseEntity<AccountDetailsResponseDTO> deposit(@RequestBody DepositRequestDTO dto) {
        return ResponseEntity.ok(service.deposit(dto));
    }
    //saque
    @PostMapping("/withdraw")
    public ResponseEntity<AccountDetailsResponseDTO> withdraw(@RequestBody WithdrawRequestDTO dto) {
        return ResponseEntity.ok(service.withdraw(dto));
    }
}
