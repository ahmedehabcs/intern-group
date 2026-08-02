package Entities.catalog;


import Entities.extra.Governorate;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    // Id PRIMARY KEY AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name NOT NULL
    @Column(nullable = false)
    private String name;

    // Phone (Unique) NOT NULL
    @Column(nullable = false, unique = true)
    private String phone;

    // Email NOT NULL (Unique)
    @Column(nullable = false, unique = true)
    private String email;

    // Address NOT NULL
    @Column(nullable = false)
    private String address;

    // Governorate_id FOREIGN KEY
    // عملناها كعلاقة عشان نقدر نجيب اسم المحافظة بسهولة في الفرونت إند
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "governorate_id", nullable = false)
    private Governorate governorate;

    // Description (يقبل Null عادي)
    @Column(columnDefinition = "TEXT")
    private String description;

    // logoURL
    private String logoUrl;

    // Is_active BOOLEAN DEFAULT TRUE
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = true;

    // ==========================================
    // Constructors, Getters, and Setters
    // ==========================================

    public Restaurant() {
    }

    // (تقدر تضيف الـ Getters والـ Setters هنا أو تستخدم @Data من Lombok)
}






