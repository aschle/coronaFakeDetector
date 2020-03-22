package org.wirvsvirus.mdatw.coronafakedetector.rest;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wirvsvirus.mdatw.coronafakedetector.articles.Article;
import org.wirvsvirus.mdatw.coronafakedetector.articles.ArticleService;
import org.wirvsvirus.mdatw.coronafakedetector.websites.Website;
import org.wirvsvirus.mdatw.coronafakedetector.websites.WebsiteService;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    WebsiteService websiteService;

    @Autowired
    ArticleService articleService;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/checkURL")
    public ResponseEntity<RestResponse> checkURL(@RequestParam String URL) {
        URL url = parseURL(URL);
        String hostname = url.getHost();
        Object toReturn;

        Website website;
        if (websiteService.alreadySeenWebsite(hostname)) {
            website = websiteService.findByURL(hostname);
        } else {
            website = new Website(hostname);
            websiteService.insert(website);
        }
        toReturn = website;

        String urlPath = url.getPath();
        if (urlPath == null || urlPath.equals("")) {
            logger.info("No path in URL. Checking only website");
        } else {
            logger.info("Path found. Checking article");

            String hash = Hashing.sha256().hashBytes(urlPath.getBytes()).toString();
            Article article = null;
            if (articleService.alreadySeenArticle(hash)) {
                article = articleService.findBySHA256(hash);
            } else {
                article = new Article();
                article.setParent(website);
                article.setSHA265(hash);
                article.setAuthor("Unknown");


                // process article for fakeness

                articleService.insert(article);
            }

            toReturn = article;
        }




        return ResponseEntity.ok(new RestResponse(toReturn));
    }

    @GetMapping("/checkPhoto")
    public ResponseEntity<RestResponse> checkPhoto(@RequestBody JsonRequest request, HttpServletRequest httpServletRequest) {
        logger.warn(httpServletRequest.getHeader("Content-Type"));

        String base64 = (String) request.getPhoto();
        String hash = Hashing.sha256().hashBytes(base64.getBytes()).toString();

        return ResponseEntity.ok(new RestResponse(hash));
    }

    @GetMapping("/checkText")
    public ResponseEntity<RestResponse> checkText(@RequestBody JsonRequest request, HttpServletRequest httpServletRequest) {
        logger.warn(httpServletRequest.getHeader("Content-Type"));

        return ResponseEntity.ok(new RestResponse(request.getText()));
    }

    private URL parseURL(String URL) {
        if (!(URL.contains("https://") || URL.contains("http://"))) {
            URL = "https://" + URL;
        }
        URL url = null;
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            // throw new
        }
        return url;
    }
}
