package com.challenge.alta.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table
@Entity
public class User extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4012497394433298032L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {
                CascadeType.PERSIST,
                CascadeType.REFRESH,
                CascadeType.MERGE,
                CascadeType.REMOVE})
    private List<Loan> loans;

    public User() {
    }

    public User(long id, String email, String firstName, String lastName, List<Loan> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
