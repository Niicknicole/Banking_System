package banking_system.dto.account;

public record AccountDetailsRequestDTO(
    Long customerId,
    String accountNumber
) {}
