package Entities.user;
import jakarta.persistence.*;

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

    // --- كود العلاقة (One-to-One) العكسية ---
    // mappedBy بتقول لسبرينج: "ماتعملش عمود هنا، العلاقة دي بتدار من هناك"
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerProfile customerProfile;

    // Getters and Setters...
}