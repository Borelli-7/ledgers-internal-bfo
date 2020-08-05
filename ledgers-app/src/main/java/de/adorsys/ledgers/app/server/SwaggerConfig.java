package de.adorsys.ledgers.app.server;

import de.adorsys.ledgers.app.server.auth.KeycloakConfigProperties;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.APIKEY;


@Configuration
//@EnableSwagger2
@RequiredArgsConstructor
@SecurityScheme(
        type = APIKEY,
        name = "Authorization",
        scheme = "Bearer",
        in = SecuritySchemeIn.HEADER)
public class SwaggerConfig implements WebMvcConfigurer {
    private static final String API_KEY = "apiKey";
    private static final String API_INFO = "api_info.txt";

    private final FileReader fileReader;
    private final BuildProperties buildProperties;
    private final Environment env;
    private final KeycloakConfigProperties keycloakConfigProp;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                       .info(new Info().title("Ledgers")
                                     .contact(new Contact()
                                                      .name("Adorsys GmbH")
                                                      .url("https://www.adorsys.de")
                                                      .email("fpo@adorsys.de"))
                                     .description(fileReader.getStringFromFile(API_INFO))
                                     .termsOfService("Terms of Service: to be edited...")
                                     .version(buildProperties.getVersion() + " " + buildProperties.get("build.number"))
                                     .license(new License()
                                                      .name("Apache License Version 2.0")
                                                      .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                       .clientId(keycloakConfigProp.getResource())
//                       .clientSecret(keycloakConfigProp.getCredentials().getSecret())
//                       .realm(keycloakConfigProp.getRealm())
//                       .appName(keycloakConfigProp.getResource())
//                       .scopeSeparator(",")
//                       .useBasicAuthenticationWithAccessCodeGrant(false)
//                       .build();
//    }
//
//    private OAuth securitySchema() {
//        GrantType grantType = new AuthorizationCodeGrantBuilder()
//                                      .tokenEndpoint(new TokenEndpoint(keycloakConfigProp.getRootPath() + "/protocol/openid-connect/token", "Bearer"))
//                                      .tokenRequestEndpoint(new TokenRequestEndpoint(keycloakConfigProp.getRootPath() + "/protocol/openid-connect/auth", keycloakConfigProp.getResource(), keycloakConfigProp.getCredentials().getSecret()))
//                                      .build();
//        return new OAuthBuilder()
//                       .name(OAUTH2)
//                       .grantTypes(singletonList(grantType))
//                       .scopes(scopes())
//                       .build();
//    }
//
//    private List<AuthorizationScope> scopes() {
//        return singletonList(new AuthorizationScope("global", "accessEverything"));
//    }

//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                       .groupName("001 - LEDGERS API")
//                       .select()
//                       .apis(resolvePredicates())
//                       .paths(PathSelectors.any())
//                       .build()
//                       .pathMapping("/")
//                       .apiInfo(metaData())
//                       .securitySchemes(Collections.singletonList(apiKey()))
//                       .securityContexts(Collections.singletonList(securityContext()));
//
//    }
//
//    private Predicate<RequestHandler> resolvePredicates() {
//        List<String> profiles = Arrays.asList(env.getActiveProfiles());
//        return profiles.contains("develop") || profiles.contains("sandbox")
//                       ? RequestHandlerSelectors.withClassAnnotation(MiddlewareUserResource.class).or(
//                                       RequestHandlerSelectors.withClassAnnotation(MiddlewareResetResource.class))
//                       : RequestHandlerSelectors.withClassAnnotation(MiddlewareUserResource.class);
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey(API_KEY, "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                       .securityReferences(defaultAuth())
//                       .forPaths(PathSelectors.regex("/*"))
//                       .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(new SecurityReference(API_KEY, authorizationScopes));
//    }
//
//    private ApiInfo metaData() {
//        Contact contact = new Contact("Adorsys GmbH", "https://www.adorsys.de", "fpo@adorsys.de");
//
//        return new ApiInfo(
//                "Ledgers", fileReader.getStringFromFile(API_INFO),
//                buildProperties.getVersion() + " " + buildProperties.get("build.number"),
//                "Terms of Service: to be edited...",
//                contact,
//                "Apache License Version 2.0",
//                "https://www.apache.org/licenses/LICENSE-2.0",
//                new ArrayList<>());
//    }
}