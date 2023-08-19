package de.hf.myfinance.composite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    /*@Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }*/

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http) {

        http
                //.cors(config->config.configurationSource(corsConfigurationSource()))
                .cors(cors -> cors.disable())
                .authorizeExchange((authorize) -> authorize
                        //.pathMatchers(HttpMethod.GET, "/mf/**").hasAuthority("SCOPE_read")
                        //.pathMatchers(HttpMethod.POST, "/mf/**").hasAuthority("SCOPE_write")
                        // the browser makes a prerequest with options but with no Authorization header, so this has to be permitted
                        .pathMatchers(HttpMethod.OPTIONS, "/mf/**").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/mf/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer((resourceserver) -> resourceserver
                    .jwt(withDefaults())
                );
        return http.build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedHeader("Requestor-Type");
        configuration.addExposedHeader("X-Get-Header");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }

}



