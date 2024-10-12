package kz.mb.project.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final SecurityConfigurationProperties configurationProperties;
  @Value("${okta.oauth2.issuer}")
  private String issuer;
  @Value("${okta.oauth2.client-id}")
  private String clientId;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(
        configurationProperties.ignoreUrl
    );
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .oauth2Login(withDefaults())
        .logout(logout -> logout
            .addLogoutHandler(logoutHandler()));
    return http.build();
  }

  @ConfigurationProperties("app.security")
  public record SecurityConfigurationProperties(
      String[] ignoreUrl
  ) {

  }

  private LogoutHandler logoutHandler() {
    return (request, response, authentication) -> {
      try {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
  }
}
