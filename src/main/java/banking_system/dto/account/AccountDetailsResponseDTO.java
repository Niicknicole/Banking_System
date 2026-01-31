package banking_system.dto.account;

import java.math.BigDecimal;

public record AccountDetailsResponseDTO(
        Long id,
        String accountNumber,
        String branch,
        BigDecimal balance,
        Long customerId
) {}
