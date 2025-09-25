package dev.samuel.school_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(configurer -> configurer.loginPage("/login")) // enables login by form authorization
                .httpBasic(Customizer.withDefaults()) // enables HTTP Basic authorization
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/professors/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE, "/professors/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/professors/**").hasRole("ADMIN");
                    authorize.requestMatchers("/professors/**").hasAnyRole("USER", "ADMIN");
                    authorize.requestMatchers("/students/**").hasAnyRole("USER", "ADMIN");
                    authorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails u1 = User.builder().username("user").password(encoder.encode("123")).roles("USER").build();
        UserDetails u2 = User.builder().username("admin").password(encoder.encode("321")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(u1, u2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
