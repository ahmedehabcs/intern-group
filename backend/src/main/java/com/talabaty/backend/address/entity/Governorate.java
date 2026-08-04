package com.talabaty.backend.address.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "governorates")
public class Governorate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


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

    @Override
    public String toString() {
        return "Governorate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
