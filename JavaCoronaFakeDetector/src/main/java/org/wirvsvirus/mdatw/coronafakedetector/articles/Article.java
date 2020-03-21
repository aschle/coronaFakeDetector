package org.wirvsvirus.mdatw.coronafakedetector.articles;

import org.wirvsvirus.mdatw.coronafakedetector.common.DomainEntity;
import org.wirvsvirus.mdatw.coronafakedetector.websites.Website;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Article extends DomainEntity {
    String author;

    @ManyToOne
    Website parent;
    double fakeProbability;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Website getParent() {
        return parent;
    }

    public void setParent(Website parent) {
        this.parent = parent;
    }

    public double getFakeProbability() {
        return fakeProbability;
    }

    public void setFakeProbability(double fakeProbability) {
        this.fakeProbability = fakeProbability;
    }
}
