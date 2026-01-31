package banking_system.dto.account;

import java.math.BigDecimal;

public record WithdrawRequestDTO(String accountNumber, BigDecimal amount) { }

