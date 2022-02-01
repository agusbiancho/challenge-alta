package com.challenge.alta.model;

import java.io.Serializable;

public class LoanInfoResponse implements Serializable {

    private static final long serialVersionUID = -8691448979724912693L;

    private long id;
    private float total;
    private long userId;

    public LoanInfoResponse() {
    }

    public LoanInfoResponse(long id, float total, long userId) {
        this.id = id;
        this.total = total;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
