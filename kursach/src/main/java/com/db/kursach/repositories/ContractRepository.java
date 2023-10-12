package com.db.kursach.repositories;

import com.db.kursach.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
     List<Contract> findByEmployeeEmail(String email);
}
