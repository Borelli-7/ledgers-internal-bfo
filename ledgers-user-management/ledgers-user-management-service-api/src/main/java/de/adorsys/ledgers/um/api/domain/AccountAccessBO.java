package de.adorsys.ledgers.um.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Currency;

import static de.adorsys.ledgers.um.api.domain.AccessTypeBO.OWNER;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AccountAccessBO {
    private String id;
    private String iban;
    private Currency currency;
    private AccessTypeBO accessType;
    private int scaWeight;
    private String accountId;
    private LocalDateTime created;

    public AccountAccessBO(@NotNull String iban, AccessTypeBO accessType) {
        this.iban = iban;
        this.accessType = accessType;
    }

    public AccountAccessBO(String accNbr, Currency currency, String accountId, int scaWeight) {
        this.accessType = OWNER;
        this.iban = accNbr;
        this.currency = currency;
        this.accountId = accountId;
        this.scaWeight = scaWeight;
    }

    @JsonIgnore
    public AccountAccessBO setWeight(int scaWeight) {
        this.scaWeight = scaWeight;
        return this;
    }
}