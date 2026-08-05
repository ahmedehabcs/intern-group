package com.talabaty.backend.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.talabaty.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}