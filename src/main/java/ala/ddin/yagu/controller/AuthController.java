package ala.ddin.yagu.controller;


import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.payload.ApiResponse;
import ala.ddin.yagu.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/reg-log")
    public ResponseEntity<ApiResponse> regLogUser(@RequestHeader String phoneNumber, @RequestHeader Role role) {
        return authService.regLog(phoneNumber , role);
    }
}
