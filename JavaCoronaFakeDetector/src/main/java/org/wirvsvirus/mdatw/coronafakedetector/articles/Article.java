package org.wirvsvirus.mdatw.coronafakedetector.articles;

import org.wirvsvirus.mdatw.coronafakedetector.common.DomainEntity;
import org.wirvsvirus.mdatw.coronafakedetector.websites.Website;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Article extends DomainEntity {
    String author;

    @ManyToOne
    Website parent;
    int requests;
    float fakeProbability;
}
