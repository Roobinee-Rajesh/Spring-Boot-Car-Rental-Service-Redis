package com.restapi.repository;

import com.restapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for interacting with the database for AppUser entities.
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    /**
     * Finds an AppUser by its username.
     *
     * @param username The username of the user to search for.
     * @return An Optional containing the found AppUser, or empty if not found.
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * Finds all staff members based on their role ID.
     *
     * @return A list of AppUser objects representing staff members.
     */
    @Query("SELECT u FROM AppUser u WHERE u.roles.id = 3")
    List<AppUser> findAllStaffMembers();


    /**
     * Finds all users based on their role ID.
     *
     * @return A list of AppUser objects representing all users.
     */
    @Query("SELECT u FROM AppUser u WHERE u.roles.id = 1")
    List<AppUser> findAllUsers();
}
