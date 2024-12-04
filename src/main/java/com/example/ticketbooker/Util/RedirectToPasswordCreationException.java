package com.example.ticketbooker.Util;

public class RedirectToPasswordCreationException extends RuntimeException {
    private String redirectUrl;

    public RedirectToPasswordCreationException(String redirectUrl) {
        super("Redirect to password creation: " + redirectUrl);
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}