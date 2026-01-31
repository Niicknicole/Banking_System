package banking_system.controller;

import banking_system.dto.CustomerRequestDTO;
import banking_system.dto.CustomerResponseDTO;
import banking_system.model.Customer;
import banking_system.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO saved = customerService.createCustomer(dto);
        return ResponseEntity.ok(saved);
    }
    
@GetMapping("/{email}")
public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable String email) {

    Customer customer = customerService.findByEmail(email);

    if (customer == null) {
        return ResponseEntity.notFound().build();
    }

    CustomerResponseDTO response = new CustomerResponseDTO(
        customer.getId(),
        customer.getName(),
        customer.getEmail()
    );

    return ResponseEntity.ok(response);
}
}
