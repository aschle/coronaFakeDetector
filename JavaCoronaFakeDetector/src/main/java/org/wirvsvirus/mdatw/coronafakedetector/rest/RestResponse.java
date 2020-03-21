package org.wirvsvirus.mdatw.coronafakedetector.rest;

import java.time.LocalDateTime;

public class RestResponse {
    LocalDateTime timestamp = LocalDateTime.now();
    Object response;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
