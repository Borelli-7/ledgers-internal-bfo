/*
 * Copyright (c) 2018-2024 adorsys GmbH and Co. KG
 * All rights are reserved.
 */

package de.adorsys.ledgers.keycloak.client.mapper;

import de.adorsys.ledgers.keycloak.client.model.KeycloakClient;
import de.adorsys.ledgers.keycloak.client.model.KeycloakRealm;
import de.adorsys.ledgers.keycloak.client.model.KeycloakUser;
import org.apache.commons.collections4.CollectionUtils;
import org.keycloak.representations.idm.*;//NOPMD
import org.mapstruct.Mapper;

import java.util.*;//NOPMD

@Mapper(componentModel = "spring")
public interface KeycloakDataMapper {

    default KeycloakUser toKeycloakUser(UserRepresentation userRepresentation) {
        return new KeycloakUser(
                userRepresentation.getId(),
                userRepresentation.getUsername(),
                null,
                userRepresentation.isEnabled(),
                userRepresentation.getFirstName(),
                userRepresentation.getLastName(),
                userRepresentation.getEmail(),
                userRepresentation.isEmailVerified(),
                userRepresentation.getRealmRoles()
        );
    }

    default UserRepresentation toUpdateUserPresentation(UserRepresentation userRepresentation, KeycloakUser user) {
        userRepresentation.setUsername(user.getLogin());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEnabled(user.getEnabled());
        userRepresentation.setEmailVerified(user.getEmailVerified());
        userRepresentation.setEmail(user.getEmail());
        List<String> realmRoles = user.getRealmRoles();
        userRepresentation.setRealmRoles(CollectionUtils.isNotEmpty(realmRoles) ? realmRoles : Collections.emptyList());
        return userRepresentation;
    }

    RealmRepresentation createRealmRepresentation(KeycloakRealm source);

    default RoleRepresentation createRoleRepresentation(String realmRole) {
        RoleRepresentation rr = new RoleRepresentation();
        rr.setName(realmRole);
        rr.setComposite(false);
        return rr;
    }

    default ClientRepresentation createClientRepresentation(KeycloakClient client) {
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(client.getClientId());
        clientRepresentation.setName(client.getClientId());
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setSecret(client.isPublicClient() ? null : client.getClientSecret());
        clientRepresentation.setWebOrigins(client.getWebOrigins());
        clientRepresentation.setPublicClient(client.isPublicClient());
        clientRepresentation.setFullScopeAllowed(client.isFullScopeAllowed());
        clientRepresentation.setAuthorizationServicesEnabled(Boolean.TRUE);
        clientRepresentation.setServiceAccountsEnabled(Boolean.TRUE);

        List<String> redirectUrls = client.getRedirectUrls();
        clientRepresentation.setRedirectUris(CollectionUtils.isNotEmpty(redirectUrls) ? redirectUrls : Collections.emptyList());
        return clientRepresentation;
    }

    default UserRepresentation createUserRepresentation(KeycloakUser user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getLogin());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEnabled(user.getEnabled());
        userRepresentation.setClientRoles(new HashMap<>());
        List<String> realmRoles = user.getRealmRoles();
        userRepresentation.setRealmRoles(CollectionUtils.isNotEmpty(realmRoles) ? realmRoles : Collections.emptyList());
        userRepresentation.setEmailVerified(user.getEmailVerified());

        userRepresentation.setCredentials(new ArrayList<>());
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(user.getPassword());
        credential.setTemporary(false);

        userRepresentation.getCredentials().add(credential);
        return userRepresentation;
    }

    default ClientScopeRepresentation createClientScopeRepresentation(String name) {
        ClientScopeRepresentation clientScopeRepresentation = new ClientScopeRepresentation();
        clientScopeRepresentation.setName(name);
        clientScopeRepresentation.setProtocol("openid-connect");
        Map<String, String> attributes = new HashMap<>();
        attributes.put("display.on.consent.screen", "true");
        attributes.put("include.in.token.scope", "true");
        clientScopeRepresentation.setAttributes(attributes);
        return clientScopeRepresentation;
    }
}
