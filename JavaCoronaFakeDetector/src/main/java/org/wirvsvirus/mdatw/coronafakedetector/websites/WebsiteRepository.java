package org.wirvsvirus.mdatw.coronafakedetector.websites;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsiteRepository extends CrudRepository<Website, String> {
    List<Website> findAllByURL(String URL);

}
