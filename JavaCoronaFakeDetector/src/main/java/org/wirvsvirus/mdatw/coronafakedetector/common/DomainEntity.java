package org.wirvsvirus.mdatw.coronafakedetector.common;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class DomainEntity {
    @Id
    protected String ID = UUID.randomUUID().toString();

    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime lastAccessed = LocalDateTime.now();
    protected int timesChecked = 1;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public int getTimesChecked() {
        return timesChecked;
    }

    public void increaseTimesChecked() {
        this.timesChecked++;
    }
}
