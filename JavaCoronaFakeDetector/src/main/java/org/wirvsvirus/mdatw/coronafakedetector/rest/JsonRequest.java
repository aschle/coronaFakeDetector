package org.wirvsvirus.mdatw.coronafakedetector.rest;

public class JsonRequest {
    Object photo;
    Object text;

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }
}
