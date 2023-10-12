package com.db.kursach.repositories;

import com.db.kursach.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByLogin(String login);
    User findByEmployeeId(Long id);
}
