package ala.ddin.yagu.service.impl;

import ala.ddin.yagu.entity.User;
import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.payload.ApiResponse;
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

    private Integer smsSender(String phoneNumber) {
        int code = new Random().nextInt(9999);
        if (code < 1000)
            code += 1000;
        SmsServiceUtil.sendSMS(phoneNumber, "Your code : " + code);
        return code;
    }


}
