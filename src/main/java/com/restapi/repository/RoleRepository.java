package com.restapi.repository;

import com.restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Finds a Role by its name.
     *
     * @param name The name of the role to search for.
     * @return The found Role, or null if not found.
     */
    Role findByName(String name);
}
