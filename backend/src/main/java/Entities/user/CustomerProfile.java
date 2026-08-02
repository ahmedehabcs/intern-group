package Entities.user;
import jakarta.persistence.*;

@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // الداتا الخاصة بالعميل
    private String defaultAddress;
    private Integer loyaltyPoints;

    // --- كود العلاقة (One-to-One) ---
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Getters and Setters...
}
