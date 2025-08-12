package com.bashverse.quicklink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShortenRequest {
    @NotBlank
    private String url;

    @Size(min = 3, max = 50)
    private String customAlias; // optional

}
