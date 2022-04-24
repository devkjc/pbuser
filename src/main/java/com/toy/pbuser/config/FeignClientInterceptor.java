package com.toy.pbuser.config;

import com.toy.pbuser.config.security.models.Credentials;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() instanceof Credentials) {
            Credentials credentials = (Credentials) authentication.getCredentials();
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, credentials.getIdToken()));
            requestTemplate.header("uid", credentials.getDecodedToken().getUid());
        }
    }
}
