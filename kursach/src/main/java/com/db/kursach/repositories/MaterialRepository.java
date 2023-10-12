package com.db.kursach.repositories;


import com.db.kursach.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Material> findByNameContaining(String name);
    List<Material> findByNameContainingOrderByPriceDesc(String name);
}
