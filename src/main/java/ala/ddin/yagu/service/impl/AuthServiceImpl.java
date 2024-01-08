package ala.ddin.yagu.service.impl;

import ala.ddin.yagu.config.SecurityConfiguration;
import ala.ddin.yagu.entity.User;
import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.exception.ForbiddenException;
import ala.ddin.yagu.jwt.JwtProvider;
import ala.ddin.yagu.payload.ApiResponse;
import ala.ddin.yagu.payload.RegisterDTO;
import ala.ddin.yagu.repository.UserRepository;
import ala.ddin.yagu.service.AuthService;
import ala.ddin.yagu.utils.SmsServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<ApiResponse> regLog(String phoneNumber, Role role) {
        Optional<User> optionalUser = userRepository.findByPhoneNumberAndRole(phoneNumber, role);
        Integer code = smsSender(phoneNumber);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setIsEnabled(false);
            user.setSmsCode(code);
            userRepository.save(user);
            return ResponseEntity.ok(ApiResponse.builder().status(200).message("Sms code sent").build());
        }
        userRepository.save(User.builder().smsCode(code).phoneNumber(phoneNumber).isEnabled(false).role(role).isConfirmed(role != Role.ROLE_DRIVER).build());
        return ResponseEntity.ok(ApiResponse.builder().status(201).message("Sms code sent").build());
    }

    @Override
    public ResponseEntity<ApiResponse> confirm(String phoneNumber, Integer code) {
        User user = userRepository.findByPhoneNumberAndSmsCode(phoneNumber, code).orElseThrow(() -> new ForbiddenException("Tasdiqlanmadi"));
        user.setIsEnabled(true);
        user.setSmsCode(null);
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.builder().message("Tasdiqlandi").status(user.getName() != null ? 200 : 201).object(jwtProvider.generateToken(user)).build());
    }

    @Override
    public ResponseEntity<ApiResponse> addInformation(RegisterDTO registerDTO) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        systemUser.setName(registerDTO.getName());
        systemUser.setSurname(registerDTO.getSurname());
        userRepository.save(systemUser);
        return ResponseEntity.ok(ApiResponse.builder().status(200).object("Malumotlar qoshildi").build());
    }

    private Integer smsSender(String phoneNumber) {
        int code = new Random().nextInt(9999);
        if (code < 1000)
            code += 1000;
        SmsServiceUtil.sendSMS(phoneNumber, "Your code : " + code);
        return code;
    }


}
