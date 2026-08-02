package Entities.Marketing;

import jakarta.persistence.*;

import java.time.LocalDateTime;



    @Entity
    @Table(name = "coupons")
    public class Coupon {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String code;

        private Double discountValue;
        private String discountType;
        private LocalDateTime expiryDate;
        private Boolean active;

        // Getters and Setters...
    }
