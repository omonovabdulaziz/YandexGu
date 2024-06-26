package ala.ddin.yagu.service;

import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.payload.ApiResponse;
import ala.ddin.yagu.payload.RegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<ApiResponse> regLog(String phoneNumber, Role role);

    ResponseEntity<ApiResponse> confirm(String phoneNumber, Integer code);

    ResponseEntity<ApiResponse> addInformation(RegisterDTO registerDTO);

}
