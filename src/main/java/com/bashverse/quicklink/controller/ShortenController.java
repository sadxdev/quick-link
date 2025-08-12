package com.bashverse.quicklink.controller;

import com.bashverse.quicklink.dto.ShortenRequest;
import com.bashverse.quicklink.dto.ShortenResponse;
import com.bashverse.quicklink.entity.UrlMapping;
import com.bashverse.quicklink.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ShortenController {
    private final UrlService service;
    public ShortenController(UrlService service) { this.service = service; }

    @PostMapping("/api/shorten")
    public ResponseEntity<ShortenResponse> shorten(@Valid @RequestBody ShortenRequest req) {
        String host = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        UrlMapping mapping = service.createShortUrl(req, host);
        String shortUrl = host + "/" + mapping.getShortCode();
        return ResponseEntity.ok(new ShortenResponse(shortUrl));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable("code") String code) {
        UrlMapping mapping = service.findByShortCode(code);
        // increment (in prod, do async)
        service.incrementHitCount(code);
        URI uri = URI.create(mapping.getLongUrl()); // changed here
        return ResponseEntity.status(302).location(uri).build();
    }

}
