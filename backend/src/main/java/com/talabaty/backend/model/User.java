package com.talabaty.backend.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean emailVerified;

    private String otp;
    private LocalDateTime otpExpiration;
    private int otpAttemptCount;

    private String passwordResetToken;
    private LocalDateTime passwordResetTokenExpiration;
    private int passwordResetAttemptCount;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerProfile customerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DeliveryProfile deliveryProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin adminProfile;

    public User() {}

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

// Getters and Setters...

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiration() {
        return otpExpiration;
    }

    public void setOtpExpiration(LocalDateTime otpExpiration) {
        this.otpExpiration = otpExpiration;
    }

    public int getOtpAttemptCount() {
        return otpAttemptCount;
    }

    public void setOtpAttemptCount(int otpAttemptCount) {
        this.otpAttemptCount = otpAttemptCount;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public LocalDateTime getPasswordResetTokenExpiration() {
        return passwordResetTokenExpiration;
    }

    public void setPasswordResetTokenExpiration(LocalDateTime passwordResetTokenExpiration) {
        this.passwordResetTokenExpiration = passwordResetTokenExpiration;
    }

    public int getPasswordResetAttemptCount() {
        return passwordResetAttemptCount;
    }

    public void setPasswordResetAttemptCount(int passwordResetAttemptCount) {
        this.passwordResetAttemptCount = passwordResetAttemptCount;
    }

    public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(CustomerProfile customerProfile) {
        this.customerProfile = customerProfile;
    }

    public DeliveryProfile getDeliveryProfile() {
        return deliveryProfile;
    }

    public void setDeliveryProfile(DeliveryProfile deliveryProfile) {
        this.deliveryProfile = deliveryProfile;
    }

    public Admin getAdminProfile() {
        return adminProfile;
    }

    public void setAdminProfile(Admin adminProfile) {
        this.adminProfile = adminProfile;
    }
}
