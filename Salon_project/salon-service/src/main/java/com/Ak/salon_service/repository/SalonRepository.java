package com.Ak.salon_service.repository;

import com.Ak.salon_service.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalonRepository extends JpaRepository<Salon,Long> {
   Optional<Salon>  findByOwnerId(Long ownerId);

    @Query(
            """
    SELECT s
    FROM salon s
    WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(s.city) LIKE LOWER(CONCAT('%', :keyword, '%'))
"""
    )
    List<Salon> searchSalons(@Param("city") String city);
}
