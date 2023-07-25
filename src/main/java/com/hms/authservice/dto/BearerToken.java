package com.hms.authservice.dto;

import lombok.Data;

@Data
public class BearerToken {

    private String accessToken ;
    private boolean authenticated;

    public BearerToken(String accessToken , boolean authenticated) {
        this.accessToken = accessToken;
        this.authenticated = authenticated;
    }
}
