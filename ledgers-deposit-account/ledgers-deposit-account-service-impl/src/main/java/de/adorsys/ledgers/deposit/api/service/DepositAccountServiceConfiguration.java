package de.adorsys.ledgers.deposit.api.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import de.adorsys.ledgers.deposit.api.service.domain.ASPSPConfigData;
import de.adorsys.ledgers.deposit.api.service.domain.ASPSPConfigSource;
import de.adorsys.ledgers.deposit.db.EnableDepositAccountRepository;

@Configuration
@ComponentScan(basePackageClasses = DepositAccountServiceBasePackage.class)
@EnableDepositAccountRepository
public class DepositAccountServiceConfiguration {

    private final ASPSPConfigSource configSource;

    @Autowired
    public DepositAccountServiceConfiguration(ASPSPConfigSource configSource) {
        this.configSource = configSource;
    }

    @Bean
    public ASPSPConfigData configData() throws IOException {
        return configSource.aspspConfigData();
    }

}
