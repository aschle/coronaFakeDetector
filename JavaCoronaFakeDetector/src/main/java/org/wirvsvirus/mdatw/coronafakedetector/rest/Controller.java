package org.wirvsvirus.mdatw.coronafakedetector.rest;

import com.google.common.hash.Hashing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wirvsvirus.mdatw.coronafakedetector.articles.Article;
import org.wirvsvirus.mdatw.coronafakedetector.websites.Website;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<String> checkPhoto(@RequestParam MultipartFile photo) {
        String fileName = "default";
        try {
            fileName = Hashing.sha256().hashBytes(photo.getBytes()).toString();
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body("Photo could not be processed.");
        }



        return ResponseEntity.ok(fileName);
    }

    @GetMapping("/checkText")
    public ResponseEntity<String> checkText(@RequestBody String text) {

        return ResponseEntity.ok("OK");
    }
}
