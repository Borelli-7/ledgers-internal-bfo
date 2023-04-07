/*
 * Copyright (c) 2018-2023 adorsys GmbH and Co. KG
 * All rights are reserved.
 */

package de.adorsys.ledgers.app.server.auth;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.context.annotation.Bean;

@KeycloakConfiguration
public class WebSecurityConfigKeycloakAdditional {

    @Bean
    public KeycloakSpringBootConfigResolver getResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

}
