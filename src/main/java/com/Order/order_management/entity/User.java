package com.Order.order_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @Column(nullable = false, unique = true)
   private String username;
    @Column(nullable = false, unique = true)
   private String email;
    @Column(nullable = false,length = 255)
   private String password;
   @Enumerated(EnumType.STRING)
   Role role;

}
