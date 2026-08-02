package Entities.user;
import Entities.order.Order;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import Entities.order.Order;

@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {

    @Id
    private Long id;
    private String Name;

    // الداتا الخاصة بالعميل
    private String defaultAddress;
    private Integer loyaltyPoints;
    private Long PhoneNumber;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderHistory = new ArrayList<>();

    // Getters and Setters...
}
