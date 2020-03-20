package org.wirvsvirus.mdatw.coronafakedetector.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/checkURL")
    public ResponseEntity<String> checkURL(@RequestParam String URL) {

        return ResponseEntity.ok("OK");
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
