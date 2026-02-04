package com.Order.order_management.repository;

import com.Order.order_management.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long > {

    boolean existsByEmail(String email);

    boolean existsByUsername(String email);

    User getUserByEmail(@Email(message = "Email is not Valid") String email);

     Optional <User> findByEmail(@Email(message = "Email is not Valid") String email);
}
