package com.hms.authservice.repository;

import com.hms.authservice.models.Role;
import com.hms.authservice.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {

    Role findByRoleName(RoleName roleName);


}
