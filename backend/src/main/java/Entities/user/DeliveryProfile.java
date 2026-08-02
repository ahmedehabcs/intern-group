package Entities.user;

import jakarta.persistence.*;

@Entity
@Table(name = "driver_profiles")
public class DeliveryProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleType; // نوع العربية (عجلة، موتوسيكل، عربية)
    private String licenseNumber; // رقم الرخصة
    private Boolean isOnline; // هل الطيار متاح يستلم أوردرات دلوقتي؟

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    // Constructor, Getters, and Setters...
}
