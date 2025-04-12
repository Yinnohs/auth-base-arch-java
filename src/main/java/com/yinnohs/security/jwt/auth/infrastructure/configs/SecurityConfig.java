package com.yinnohs.security.jwt.auth.infrastructure.configs;

import com.yinnohs.security.jwt.auth.domain.entities.Role;
import com.yinnohs.security.jwt.auth.infrastructure.filters.JWTFilter;
import com.yinnohs.security.jwt.auth.infrastructure.services.UserDetailsAccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.yinnohs.security.jwt.auth.domain.entities.Permissions.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Value("${argon2.memory}")
    private int memory;

    @Value("${argon2.iterations}")
    private int iterations;

    @Value("${argon2.parallelism}")
    private int parallelism;

    @Value("${argon2.saltLength}")
    private int saltLength;

    private final UserDetailsAccountServiceImpl userDetailsAccountService;

    private final JWTFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->{
                    request.requestMatchers("/api/v1/auth/**").permitAll()
                            //management section
                            .requestMatchers("/api/v1/management/**")
                            .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                            .requestMatchers(HttpMethod.GET, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                            .requestMatchers(HttpMethod.POST, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                            .requestMatchers(HttpMethod.PUT, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                            //admin section
                            .requestMatchers("/api/v1/admin/**")
                            .hasRole(Role.ADMIN.name())
                            .requestMatchers(HttpMethod.GET, "/api/v1/admin/**")
                            .hasAuthority(ADMIN_READ.name())
                            .requestMatchers(HttpMethod.POST, "/api/v1/admin/**")
                            .hasAuthority(ADMIN_CREATE.name())
                            .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**")
                            .hasAuthority(ADMIN_UPDATE.name())
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**")
                            .hasAuthority(ADMIN_DELETE.name())

                            //any request
                            .anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsAccountService);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        int hashLength = 128;
        return new Argon2PasswordEncoder(
                saltLength,
                hashLength,
                parallelism,
                memory,
                iterations
        );
    }
}
