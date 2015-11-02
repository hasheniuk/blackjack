package com.blackjack.rest.config;

import com.sun.jersey.api.core.PackagesResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class ApplicationConfig extends PackagesResourceConfig {

    public ApplicationConfig() {
        super("com.blackjack.rest.service");
    }
}
