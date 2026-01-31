package banking_system.service;

import banking_system.model.Account;
import banking_system.model.Customer;
import banking_system.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DefaultAccountSetupService {

    private final AccountRepository accountRepository;

    public DefaultAccountSetupService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createDefaultAccounts(Customer customer) {
        // Gera um número de conta único
        String accountNumber = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
                
        // Define a agência baseada no ID do cliente
        String branch = String.format("%03d", customer.getId());
        Account account = new Account(customer, accountNumber, branch);
        accountRepository.save(account);
    }
}
