package com.challenge.alta.model;

import com.challenge.alta.entity.Loan;

import java.io.Serializable;
import java.util.List;

public class UserInfoResponse implements Serializable {

    private static final long serialVersionUID = 5452312079053757752L;

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<LoanInfoResponse> loans;

    public UserInfoResponse() {
    }

    public UserInfoResponse(long id, String email, String firstName, String lastName, List<LoanInfoResponse> loans) {
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

    public List<LoanInfoResponse> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanInfoResponse> loans) {
        this.loans = loans;
    }
}
