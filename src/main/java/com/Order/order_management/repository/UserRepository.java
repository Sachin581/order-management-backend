package com.Order.order_management.repository;

import com.Order.order_management.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.lang.ScopedValue;c
import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User,Long > {

    boolean existsByEmail(String email);

    boolean existsByUsername(String email);

    Optional<User> getUserByEmail(@Email(message = "Email is not Valid") String email);

     Optional <User> findByEmail(@Email(message = "Email is not Valid") String email);
}
