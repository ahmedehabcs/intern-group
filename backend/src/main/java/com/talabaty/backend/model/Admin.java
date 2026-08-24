package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
@Getter
public class Admin{

    @Id
    private Long id;
    @Setter
    private String name;
    @Setter
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Setter
    private User user;

    public Admin() {
    }

    public Admin(String name, String phoneNumber, User user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }








    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", user=" + user +
                '}';
    }
}
