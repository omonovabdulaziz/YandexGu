package ala.ddin.yagu.controller;


import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.payload.ApiResponse;
import ala.ddin.yagu.payload.RegisterDTO;
import ala.ddin.yagu.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/reg-log")
    public ResponseEntity<ApiResponse> regLogUser(@RequestHeader String phoneNumber, @RequestHeader Role role) {
        return authService.regLog(phoneNumber, role);
    }

    @PutMapping("/confirm")
    public ResponseEntity<ApiResponse> confirm(@RequestParam String phoneNumber, @RequestParam Integer code) {
        return authService.confirm(phoneNumber, code);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_DRIVER')")
    @PutMapping("/addInformation")
    public ResponseEntity<ApiResponse> addInformation(@Valid @RequestBody RegisterDTO registerDTO) {
        return authService.addInformation(registerDTO);
    }
}
