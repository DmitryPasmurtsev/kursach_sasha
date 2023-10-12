package com.db.kursach.repositories;

import com.db.kursach.models.Estimate;
import com.db.kursach.models.EstimateKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstimateRepository extends JpaRepository<Estimate, EstimateKey> {
    Optional<Estimate> findById(EstimateKey id);
}
