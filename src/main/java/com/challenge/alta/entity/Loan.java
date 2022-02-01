package com.challenge.alta.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
public class Loan extends BaseObject implements Serializable {

    private static final long serialVersionUID = 2175666406118797483L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FKUser_Loan"))
    private User user;

    public Loan() {
    }

    public Loan(long id, float total, User user) {
        super();
        this.id = id;
        this.total = total;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
