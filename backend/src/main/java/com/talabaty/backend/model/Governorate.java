package com.talabaty.backend.model;

import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;

@Entity
@Table(name = "governorates")
@Getter
@Setter
public class Governorate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    public Governorate() {
    }

    // Getters and Setters




    @Override
    public String toString() {
        return "Governorate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
