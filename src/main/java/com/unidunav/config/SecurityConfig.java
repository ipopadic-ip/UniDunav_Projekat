package com.unidunav.config;

import com.unidunav.auth.service.JwtAuthFilter;
import com.unidunav.auth.service.JwtService;
import com.unidunav.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // Ako koristiš cookies ili Authorization header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtService jwtService, UserRepository userRepository) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults()) 
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                		.anyRequest().permitAll()
//                	    .requestMatchers("/api/auth/**", "/api/public/**").permitAll()

                	    // Samo ADMIN može da pristupi ovim rutama
//                	    .requestMatchers("/api/admin/**").hasRole("ADMIN")

                	    // Samo SLUZBENIK može da pristupi ovim rutama
//                	    .requestMatchers("/api/sluzbenik/**").hasRole("SLUZBENIK")

                	    // Samo PROFESOR može da pristupi ovim rutama
//                	    .requestMatchers("/api/profesor/**").hasRole("PROFESOR")

                	    // Samo STUDENT može da pristupi ovim rutama
//                	    .requestMatchers("/api/student/**").hasRole("STUDENT")

                	    // Sve ostalo mora biti autentifikovano
//                	    .anyRequest().authenticated()
                	)

//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/**", "/api/public/**").permitAll()
//                        .anyRequest().authenticated()
//                )
                .addFilterBefore(new JwtAuthFilter(jwtService, userRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
