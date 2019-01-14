package de.adesso.cccinw4.worker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Konfiguration für den Worker Service
 * Es wird der Benutzer worker mit einem festen Passwort konfiguriert
 */
@EnableWebSecurity
@Configuration
public class WorkerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${worker.user}")
    private String workerUser;
    @Value("{noop}${worker.password}")
    private String workerPassword;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(workerUser)
                .password(workerPassword)
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
