package ala.ddin.yagu.repository;

import ala.ddin.yagu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String username);
}
