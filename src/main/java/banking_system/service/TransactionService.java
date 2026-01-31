package banking_system.service;

import banking_system.dto.TransactionRequestDTO;
import banking_system.dto.TransactionResponseDTO;
import banking_system.model.Account;
import banking_system.model.Transaction;
import banking_system.repository.AccountRepository;
import banking_system.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional 
    public TransactionResponseDTO deposit(TransactionRequestDTO dto) {
        Account account = accountRepository.findById(dto.accountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(dto.amount()));
        accountRepository.save(account);

        Transaction saved = transactionRepository.save(
                new Transaction("DEPOSIT", dto.amount(), account)
        );

        return mapToResponse(saved, account.getId());
    }

    @Transactional // evita inconsistência em caso de erro no saque
    public TransactionResponseDTO withdraw(TransactionRequestDTO dto) {
        Account account = accountRepository.findById(dto.accountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(dto.amount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(dto.amount()));
        accountRepository.save(account);

        Transaction saved = transactionRepository.save(
                new Transaction("WITHDRAW", dto.amount(), account)
        );

        return mapToResponse(saved, account.getId());
    }

    // histórico da conta
    public List<TransactionResponseDTO> getHistory(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return transactionRepository.findByAccount(account)
                .stream()
                .map(tx -> mapToResponse(tx, account.getId()))
                .collect(Collectors.toList());
    }
    private TransactionResponseDTO mapToResponse(Transaction tx, Long accountId) {
        return new TransactionResponseDTO(
                tx.getId(),
                tx.getType(),
                tx.getAmount(),
                tx.getTimestamp().toString(),
                accountId
        );
    }
}