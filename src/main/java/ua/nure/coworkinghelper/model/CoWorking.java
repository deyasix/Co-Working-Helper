package ua.nure.coworkinghelper.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "coworkings")
public class CoWorking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public CoWorking() {}
    public CoWorking(String name, String address, Customer customer) {
        this.name = name;
        this.address = address;
        this.customer = customer;
    }
}
