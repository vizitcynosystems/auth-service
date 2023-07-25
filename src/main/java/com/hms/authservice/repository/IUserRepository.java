package com.hms.authservice.repository;

import com.hms.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User , UUID> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
