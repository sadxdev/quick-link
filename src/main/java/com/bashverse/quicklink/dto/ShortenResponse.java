package com.bashverse.quicklink.dto;

public class ShortenResponse {
    private String shortUrl;
    public ShortenResponse(String shortUrl){ this.shortUrl = shortUrl; }
    public String getShortUrl(){ return shortUrl; }
}
