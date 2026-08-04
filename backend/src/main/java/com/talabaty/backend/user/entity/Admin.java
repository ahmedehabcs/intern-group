package com.talabaty.backend.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
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

    public Admin(String name, String phoneNumber, User user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
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
