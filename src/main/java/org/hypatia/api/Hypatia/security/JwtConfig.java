package org.hypatia.api.Hypatia.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {


    private String secret;

    private long expiration;

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Date getExpirationDate() {
        long expirationTimeInMilliseconds = Calendar.getInstance().getTimeInMillis() + expiration;
        return new Date(expirationTimeInMilliseconds);
    }
}
