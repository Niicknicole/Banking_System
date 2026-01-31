package banking_system.service;

import banking_system.dto.AuthRequestDTO;
import banking_system.dto.AuthResponseDTO;
import banking_system.model.Customer;
import banking_system.repository.CustomerRepository;
import banking_system.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(CustomerRepository customerRepository,
                       BCryptPasswordEncoder encoder,
                       JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {

    Customer customer = customerRepository.findByEmail(dto.email())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

     // Validando a senha criptografada
    if (!encoder.matches(dto.password(), customer.getPassword())) {
        throw new RuntimeException("Senha inválida");
    }

    // Gera o token JWT para autenticação
    String token = jwtUtil.generateToken(customer);

    return new AuthResponseDTO(token, customer.getEmail());
}

}


