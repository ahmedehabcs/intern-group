package Entities.extra;


import jakarta.persistence.*;

@Entity
@Table(name = "governorates")
public class Governorate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // اسم المحافظة (مينفعش يتكرر)
    @Column(nullable = false, unique = true)
    private String name;

    // ممكن مستقبلاً تضيف أعمدة زي:
    // private BigDecimal baseDeliveryFee;

    public Governorate() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
