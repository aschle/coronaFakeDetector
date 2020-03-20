package org.wirvsvirus.mdatw.coronafakedetector.websites;

import org.wirvsvirus.mdatw.coronafakedetector.common.DomainEntity;
import org.wirvsvirus.mdatw.coronafakedetector.articles.Article;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Website extends DomainEntity {
    @Column
    String URL;

    @OneToMany
    List<Article> articles;


}
