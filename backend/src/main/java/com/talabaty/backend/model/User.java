package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private Role role;

    @Column(nullable = false)
    @Setter
    private boolean emailVerified;

    @Setter
    private String otp;
    @Setter
    private LocalDateTime otpExpiration;
    @Setter
    private int otpAttemptCount;

    @Setter
    private String passwordResetToken;
    @Setter
    private LocalDateTime passwordResetTokenExpiration;
    @Setter
    private int passwordResetAttemptCount;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter
    private CustomerProfile customerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter
    private DeliveryProfile deliveryProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter
    private Admin adminProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter
    private KitchenManager kitchenManagerProfile;

    public User() {}

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

// Getters and Setters...





























}
