package org.wirvsvirus.mdatw.coronafakedetector.websites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WebsiteService {

    @Autowired
    private WebsiteRepository repository;

    public List<Website> findAll() {
        return repository.findAll();
    }

    public Website findByURL(String URL) {
        return repository.findByURL(URL);
    }

    public boolean alreadySeenWebsite(String URL) {
        if (repository.existsWebsiteByURL(URL)) {
            Website website = repository.findByURL(URL);
            website.increaseTimesChecked();
            website.setLastAccessed(LocalDateTime.now());
            repository.save(website);

            return true;
        }
        return false;
    }

    public void insert(Website website) {
        repository.saveAndFlush(website);
    }

    public void update(Website website) {
        website.setLastAccessed(LocalDateTime.now());
        repository.save(website);
    }

    public void delete(Website website) {
        repository.delete(website);
    }
}
