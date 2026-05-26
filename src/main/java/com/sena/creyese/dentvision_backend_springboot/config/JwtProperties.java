package com.sena.creyese.dentvision_backend_springboot.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Component
@Validated
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    @NotBlank(message = "JWT_SECRET must be configured")
    @Size(min = 32, message = "JWT_SECRET must be at least 32 characters")
    private String secret;

    @NotNull(message = "JWT_EXPIRATION must be configured")
    @Min(value = 1, message = "JWT_EXPIRATION must be greater than zero")
    private Long expiration;
}
