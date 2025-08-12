package com.bashverse.quicklink.service.impl;

import com.bashverse.quicklink.dto.ShortenRequest;
import com.bashverse.quicklink.entity.UrlMapping;
import com.bashverse.quicklink.repository.UrlMappingRepository;
import com.bashverse.quicklink.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlMappingRepository urlMappingRepository;

    @Override
    public UrlMapping createShortUrl(ShortenRequest request, String baseUrl) {
        String shortCode = StringUtils.hasText(request.getCustomAlias())
                ? request.getCustomAlias()
                : generateShortCode();

        UrlMapping mapping = UrlMapping.builder()
                .shortCode(shortCode)
                .longUrl(request.getUrl()) //
                .createdAt(LocalDateTime.now())
                .hitCount(0L)
                .build();

        return urlMappingRepository.save(mapping);
    }

    @Override
    public UrlMapping findByShortCode(String shortCode) {
        return urlMappingRepository.findByShortCode(shortCode)
                .orElse(null);
    }

    @Override
    public void incrementHitCount(String shortCode) {
        Optional<UrlMapping> mappingOpt = urlMappingRepository.findByShortCode(shortCode);
        mappingOpt.ifPresent(mapping -> {
            mapping.setHitCount(mapping.getHitCount() + 1);
            urlMappingRepository.save(mapping);
        });
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}