package com.Order.order_management.repository;

import com.Order.order_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long > {

    boolean existsByEmail(String email);

    boolean existsByUsername(String email);
}
