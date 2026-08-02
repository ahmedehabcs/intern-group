package Entities.catalog;


import Entities.extra.Governorate;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "governorate_id", nullable = false)
    private Governorate governorate;

    @Column(columnDefinition = "TEXT")
    private String description;


    private String logoUrl;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = true;


    // Constructors, Getters, and Setters


    public Restaurant() {
    }

}






