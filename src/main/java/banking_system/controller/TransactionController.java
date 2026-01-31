package banking_system.controller;

import banking_system.dto.TransactionRequestDTO;
import banking_system.dto.TransactionResponseDTO;
import banking_system.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDTO> deposit(@RequestBody TransactionRequestDTO dto) {
        return ResponseEntity.ok(service.deposit(dto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDTO> withdraw(@RequestBody TransactionRequestDTO dto) {
        return ResponseEntity.ok(service.withdraw(dto));
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> history(@PathVariable Long accountId) {
        return ResponseEntity.ok(service.getHistory(accountId));
    }
}
