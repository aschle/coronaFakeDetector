package org.wirvsvirus.mdatw.coronafakedetector.articles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {

    Article findAllBySHA265(String SHA256);
    boolean existsBySHA265(String SHA256);
}
