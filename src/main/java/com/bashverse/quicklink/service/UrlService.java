package com.bashverse.quicklink.service;

import com.bashverse.quicklink.dto.ShortenRequest;
import com.bashverse.quicklink.entity.UrlMapping;

public interface UrlService {

    UrlMapping createShortUrl(ShortenRequest request, String baseUrl);

    UrlMapping findByShortCode(String shortCode);

    void incrementHitCount(String shortCode);
}
