package com.challenge.alta.model;

import java.io.Serializable;

public class UserRequest implements Serializable {

    private static final long serialVersionUID = -1717840537138909266L;

    private long id;
    private String email;
    private String firstName;
    private String lastName;

    public UserRequest() {
    }

    public UserRequest(long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
