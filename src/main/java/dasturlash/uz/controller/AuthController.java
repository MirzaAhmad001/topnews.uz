package dasturlash.uz.controller;


import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PostMapping("/login/{username}/{password}")
    public ResponseEntity<String> login(@PathVariable("username") String username, @PathVariable("password") String password) {
        return ResponseEntity.ok(authService.login(username, password));
    }

    @GetMapping("/registration/email/verification/{token}")
    public ResponseEntity<String> registration(@PathVariable("token") String token) {
        return ResponseEntity.ok(authService.regEmailVerification(token));
    }

    @GetMapping("/registration/sms/verification/{token}")
    public ResponseEntity<String> registrationSms(@PathVariable("token") String token) {
        return ResponseEntity.ok(authService.regSmsVerification(token));
    }
}
