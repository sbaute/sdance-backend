package com.sdance_backend.sdance.security.filter;


import com.sdance_backend.sdance.messages.ResponseBuilderMessage;
import com.sdance_backend.sdance.messages.errors.AuthError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class HttpSecurityConfig {

    private final AuthenticationProvider daoAuthProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ResponseBuilderMessage responseBuilderMessage;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) ->
                                responseBuilderMessage.writeHttpError(
                                        response,
                                        AuthError.AUTH_UNAUTHORIZED
                                )
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                responseBuilderMessage.writeHttpError(
                                        response,
                                        AuthError.AUTH_FORBIDDEN
                                )
                        )
                )

                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    // Rutas de autenticación
                    auth.requestMatchers(HttpMethod.POST, "/api/v1/user/register/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/authenticate").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/validate").permitAll();

                    auth.anyRequest().authenticated();
                    //auth.anyRequest().permitAll();
                })
                .build();
        /**http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();**/
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }





}
