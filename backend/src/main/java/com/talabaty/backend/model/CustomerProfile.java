package com.talabaty.backend.model;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "customers")
public class CustomerProfile {

    @Id
    private Long id;
    private String name;
    private Integer loyaltyPoints;
    private String phoneNumber;

    // Foreign key to the associated User
    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId

    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    // Foreign key to the associated User
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderHistory = new ArrayList<>();


    // CascadeType.ALL propagates all operations (persist, merge, remove, etc.)
    // from Customer to its Addresses.
    // orphanRemoval = true automatically deletes an Address from the database
    // if it is removed from this collection.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public CustomerProfile() {}
    public CustomerProfile(String name, Integer loyaltyPoints, String phoneNumber, User user, List<Order> orderHistory, List<Address> addresses) {
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.orderHistory = orderHistory;
        this.addresses = addresses;
    }


    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
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

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "CustomerProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", phoneNumber=" + phoneNumber +
                ", user=" + user +
                ", orderHistory=" + orderHistory +
                ", addresses=" + addresses +
                '}';
    }
}
