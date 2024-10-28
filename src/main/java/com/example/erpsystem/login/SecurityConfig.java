package com.example.erpsystem.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/createUser").hasRole("ADMIN") // tylko admin ma dostęp
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // opcjonalna strona logowania
                        .permitAll()
                )
                .logout()
                .logoutSuccessUrl("/login?logout") // przekierowanie po wylogowaniu
                .permitAll();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer =
                new JdbcUserDetailsManagerConfigurer<>();

        configurer.usersByUsernameQuery(
                "SELECT username, password, 1 FROM users WHERE username = ?"
        );

        configurer.authoritiesByUsernameQuery(
                "SELECT u.username, r.name FROM users u " +
                        "JOIN roles r ON u.role_id = r.id " +
                        "WHERE u.username = ?"
        );

        configurer.dataSource(dataSource);

        return configurer.getUserDetailsService();
    }
}

