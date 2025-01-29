package com.iagocarvalho.eccomerceapp.repositori;

import com.iagocarvalho.eccomerceapp.user.AppRole;
import com.iagocarvalho.eccomerceapp.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
