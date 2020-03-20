package org.wirvsvirus.mdatw.coronafakedetector.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @PostMapping("/checkURL")
    public ResponseEntity<String> checkURL(@RequestParam String URL) {

        return ResponseEntity.ok("OK");
    }

    @PostMapping("/checkPhoto")
    public ResponseEntity<String> checkPhoto(@RequestParam String URL) {

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/get")
    public ResponseEntity<String> checkText(@RequestBody String text) {

        return ResponseEntity.ok("OK");   }
}
