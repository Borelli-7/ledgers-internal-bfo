package de.adorsys.ledgers.middleware.impl.converter;

import de.adorsys.ledgers.keycloak.client.model.KeycloakUser;
import de.adorsys.ledgers.um.api.domain.UserBO;
import org.springframework.stereotype.Service;

@Service
public class KeycloakUserMapper {
    public KeycloakUser toKeycloakUser(UserBO source) {
        return new KeycloakUser(source.getLogin(),
                                source.getPin(),
                                true,
                                source.getLogin(),
                                null,
                                source.getEmail(),
                                true, //TODO Consider set this to false
                                source.getRolesAsString());
    }
}
