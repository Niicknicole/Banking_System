package banking_system.dto.account;

import java.math.BigDecimal;

public record DepositRequestDTO(String accountNumber, BigDecimal amount) { }
