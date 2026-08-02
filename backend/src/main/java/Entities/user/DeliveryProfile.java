package Entities.user;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import Entities.order.Order;


@Entity
@Table(name = "driver_profiles")
public class DeliveryProfile {

    @Id
    private Long id;
    private String Name;
    private String vehicleType;
    private String licenseNumber;
    private Boolean isOnline;
    private Long PhoneNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Order> deliveryHistory = new ArrayList<>();


    // Constructor, Getters, and Setters...
    }
