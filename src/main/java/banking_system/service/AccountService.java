package banking_system.service;

import banking_system.dto.account.*;
import banking_system.model.Account;
import banking_system.model.Customer;
import banking_system.repository.AccountRepository;
import banking_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountDetailsResponseDTO create(AccountDetailsRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        String generatedBranch = generateBranchForCustomer(customer);

        // Gera a agência com base no ID do cliente
        Account account = new Account(customer, dto.accountNumber(), generatedBranch);

        accountRepository.save(account);

        return mapToDTO(account);
    }

    private String generateBranchForCustomer(Customer customer) {
        return String.format("%03d", customer.getId());
    }

    public AccountDetailsResponseDTO getAccountByCustomerId(Long customerId) {
        return accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getCustomer().getId().equals(customerId))
                .findFirst()
                .map(this::mapToDTO)
                .orElse(null);
    }

    public AccountDetailsResponseDTO deposit(DepositRequestDTO dto) {
        Account account = getByAccountNumber(dto.accountNumber());
        account.setBalance(account.getBalance().add(dto.amount()));
        accountRepository.save(account);
        return mapToDTO(account);
    }

    public AccountDetailsResponseDTO withdraw(WithdrawRequestDTO dto) {
        Account account = getByAccountNumber(dto.accountNumber());

        if (account.getBalance().compareTo(dto.amount()) < 0) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        account.setBalance(account.getBalance().subtract(dto.amount()));
        accountRepository.save(account);
        return mapToDTO(account);
    }

    private Account getByAccountNumber(String accountNumber) {
        return accountRepository.findAll()
                .stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    //Converte a entidade Account para DTO
    private AccountDetailsResponseDTO mapToDTO(Account account) {
        return new AccountDetailsResponseDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getBranch(),
                account.getBalance(),
                account.getCustomer().getId()
        );
    }
}
