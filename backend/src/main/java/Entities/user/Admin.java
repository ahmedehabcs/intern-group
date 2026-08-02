package Entities.user;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_profiles")
public class Admin{

    @Id
    private Long id;
    private String name;
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Admin() {
    }

}