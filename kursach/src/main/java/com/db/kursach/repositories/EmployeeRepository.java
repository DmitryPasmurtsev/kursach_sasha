package com.db.kursach.repositories;

import com.db.kursach.models.Employee;
import com.db.kursach.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByNameContainingIgnoreCase(String fullName);

    Employee findByEmail(String email);
    List<Employee> findByPosition_Name(String name);
    List<Employee> findByNameContainingOrderByExperienceDesc(String name);

}
