package ru.bobrysheva.library_poj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.bobrysheva.library_poj.security.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(HttpMethod.POST,"/users").anonymous()
                                .requestMatchers(HttpMethod.PUT, "/users").anonymous()
                                .requestMatchers(HttpMethod.GET,"/users").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService users() {
//        PasswordEncoder encoder = passwordEncoder();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("password"))
//                .roles(String.valueOf(Role.USER))
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("password"))
//                .roles(String.valueOf(Role.USER), String.valueOf(Role.ADMIN))
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}