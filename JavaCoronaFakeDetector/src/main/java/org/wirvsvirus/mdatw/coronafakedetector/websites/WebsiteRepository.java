package org.wirvsvirus.mdatw.coronafakedetector.websites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, String> {
    Website findByURL(String URL);
    boolean existsWebsiteByURL(String URL);
}
