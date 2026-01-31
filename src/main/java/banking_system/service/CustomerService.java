package banking_system.service;

import banking_system.dto.CustomerRequestDTO;
import banking_system.dto.CustomerResponseDTO;
import banking_system.model.Customer;
import banking_system.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DefaultAccountSetupService defaultAccountSetupService;

    public CustomerService(CustomerRepository customerRepository,
                       DefaultAccountSetupService defaultAccountSetupService,
                       BCryptPasswordEncoder passwordEncoder) { // Injete aqui
    this.customerRepository = customerRepository;
    this.defaultAccountSetupService = defaultAccountSetupService;
    this.passwordEncoder = passwordEncoder;
}

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        // Criptografia de senha antes de salvar no banco
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        Customer customer = new Customer(
                dto.getName(),
                dto.getEmail(),
                encryptedPassword
        );

 
        Customer savedCustomer = customerRepository.save(customer);

        // Cria automaticamente as contas padrão do cliente
        defaultAccountSetupService.createDefaultAccounts(savedCustomer);
        return new CustomerResponseDTO(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail()
        );
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
}
