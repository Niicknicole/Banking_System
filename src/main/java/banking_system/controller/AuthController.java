package banking_system.controller;

import banking_system.dto.AuthRequestDTO;
import banking_system.dto.AuthResponseDTO;
import banking_system.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//fluxo de autenticação
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // Service responsável pela lógica de autenticação
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO dto) {
        AuthResponseDTO response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}
