package com.global.accelerex.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel implements Serializable {
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @CreatedDate
    private LocalDateTime created;


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}