package com.restapi.repository;

import com.restapi.model.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceStatusRepository extends JpaRepository<MaintenanceStatus, Integer> {

    /**
     * Finds a MaintenanceStatus by its status field.
     *
     * @param status The status to search for.
     * @return An Optional containing the found MaintenanceStatus, or empty if not found.
     */
    Optional<MaintenanceStatus> findByStatus(String status);

    /**
     * Finds a MaintenanceStatus by its ID.
     *
     * @@param id The ID to search for.
     * @return The found MaintenanceStatus, or null if not found.
     * @deprecated Use {@@link #findById(Integer)} instead, which returns an Optional.
     */
    MaintenanceStatus findById(int i);
}
