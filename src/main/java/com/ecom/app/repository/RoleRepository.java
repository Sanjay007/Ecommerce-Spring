package com.ecom.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.app.model.Role;
import com.ecom.app.model.RoleName;

import java.util.Optional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
