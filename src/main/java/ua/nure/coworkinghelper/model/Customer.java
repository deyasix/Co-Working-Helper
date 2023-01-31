package ua.nure.coworkinghelper.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "isadmin")
    private Boolean isAdmin;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "id")
    private Set<Request> requests = new HashSet<>(0);

    @OneToOne(cascade = CascadeType.PERSIST)
    private Subscription subscription;

    public void setRequests(Set<Request> children) {
        this.requests = children;
        this.requests.forEach(child -> child.setCustomer(this));
    }

    public void setSubscriptions(Subscription subscription) {
        this.subscription = subscription;
    }

    public Customer() {

    }


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;

    public Customer(String name, String password, String phone, Boolean isAdmin, String email) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.email = email;
    }
}
