package com.hatefulbug.authserver.repository;

import com.hatefulbug.authserver.entity.Role;
import com.hatefulbug.authserver.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleName name);

}
