package banking_system.dto;

import java.math.BigDecimal;

public record TransactionResponseDTO(
        Long id,
        String type,
        BigDecimal amount,
        String timestamp,
        Long accountId
) {}
