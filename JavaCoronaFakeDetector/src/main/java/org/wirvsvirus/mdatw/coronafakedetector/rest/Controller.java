package org.wirvsvirus.mdatw.coronafakedetector.rest;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/checkURL")
    public ResponseEntity<RestResponse> checkURL(@RequestParam String URL) {


        String hostname;
        URL url;
        try {
            url = new URL(URL);
            hostname = url.getHost();

        } catch (MalformedURLException e) {
            try {
                url = new URL("https://" + URL);
                hostname = url.getHost();
            } catch (MalformedURLException ex) {
                return ResponseEntity.badRequest().body(new RestResponse("ERROR: Malformed URL: " + ex.getMessage()));
            }
            return ResponseEntity.badRequest().body(new RestResponse("ERROR: Malformed URL: " + e.getMessage()));
        }


        Website website;

        if (websiteService.alreadySeenWebsite(hostname)) {
            website = websiteService.findByURL(hostname);
        } else {
            website = new Website(hostname);
            websiteService.insert(website);
        }


        return ResponseEntity.ok(new RestResponse(website));
    }

    @GetMapping("/checkPhoto")
    public ResponseEntity<RestResponse> checkPhoto(@RequestBody JsonRequest request, HttpServletRequest httpServletRequest) {

        logger.warn(httpServletRequest.getHeader("Content-Type"));


        String base64 = (String) request.getPhoto();
        String hash = Hashing.sha256().hashBytes(base64.getBytes()).toString();
        ;

        return ResponseEntity.ok(new RestResponse(hash));
    }

    @GetMapping("/checkText")
    public ResponseEntity<RestResponse> checkText(@RequestBody JsonRequest request, HttpServletRequest httpServletRequest) {

        logger.warn(httpServletRequest.getHeader("Content-Type"));


        return ResponseEntity.ok(new RestResponse(request.getText()));
    }
}
