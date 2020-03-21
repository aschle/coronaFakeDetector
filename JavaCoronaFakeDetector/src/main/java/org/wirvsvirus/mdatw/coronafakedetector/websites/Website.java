package org.wirvsvirus.mdatw.coronafakedetector.websites;

import org.wirvsvirus.mdatw.coronafakedetector.common.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Website extends DomainEntity {
    @Column
    String URL;

    public Website(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void increaseTimesChecked() {
        this.timesChecked++;
    }
}
