package org.wirvsvirus.mdatw.coronafakedetector.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wirvsvirus.mdatw.coronafakedetector.articles.Article;
import org.wirvsvirus.mdatw.coronafakedetector.websites.Website;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/checkURL")
    public ResponseEntity<RestResponse> checkURL(@RequestParam String URL) {
        RestResponse response = new RestResponse();
        Article article = new Article();
        article.setAuthor("Some Author");
        article.setFakeProbability(68);

        Website website = new Website();
        website.setURL("example.com");
        article.setParent(website);

        response.setResponse(article);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/checkPhoto")
    public ResponseEntity<String> checkPhoto(@RequestParam String URL) {

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/checkText")
    public ResponseEntity<String> checkText(@RequestBody String text) {

        return ResponseEntity.ok("OK");
    }
}
