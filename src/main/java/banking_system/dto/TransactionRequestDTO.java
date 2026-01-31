package banking_system.dto;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        Long accountId,
        BigDecimal amount
) {}
