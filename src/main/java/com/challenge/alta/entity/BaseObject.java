package com.challenge.alta.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public abstract class BaseObject {

    @CreatedDate
    private Date inserted;
    @LastModifiedDate
    private Date updated;

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @PrePersist
    public void onPrePersist() {
        this.setInserted(new Date());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setUpdated(new Date());
    }
}
