package ala.ddin.yagu.config;

import ala.ddin.yagu.entity.User;
import ala.ddin.yagu.entity.enums.Role;
import ala.ddin.yagu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {
    private final UserRepository userRepository;


    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(sqlInitMode, "always")) {
            userRepository.save(User.builder().name("YaGu").surname("YaGu").phoneNumber("+99895096053").role(Role.ROLE_ADMIN).isEnabled(true).cardNumber(123456L).balance(0L).build());
            System.out.println("Admin Saved");
        }
    }
}
