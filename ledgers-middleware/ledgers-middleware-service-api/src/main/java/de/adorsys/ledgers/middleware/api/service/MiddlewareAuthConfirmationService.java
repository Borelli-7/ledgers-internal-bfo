/*
 * Copyright (c) 2018-2024 adorsys GmbH and Co. KG
 * All rights are reserved.
 */

package de.adorsys.ledgers.middleware.api.service;

import de.adorsys.ledgers.middleware.api.domain.sca.AuthConfirmationTO;

public interface MiddlewareAuthConfirmationService {

    AuthConfirmationTO verifyAuthConfirmationCode(String authorisationId, String authConfirmCode, String userLogin);

    AuthConfirmationTO completeAuthConfirmation(String authorisationId, boolean success, String userLogin);
}
