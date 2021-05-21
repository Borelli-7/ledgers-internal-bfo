package de.adorsys.ledgers.keycloak.client.api;

import de.adorsys.ledgers.middleware.api.domain.um.BearerTokenTO;

public interface KeycloakTokenService {

    BearerTokenTO login(String login, String password);

    BearerTokenTO exchangeToken(String oldToken, Integer timeToLive, String scope);

    BearerTokenTO validate(String token);

    BearerTokenTO refreshToken(String refreshToken);
}
