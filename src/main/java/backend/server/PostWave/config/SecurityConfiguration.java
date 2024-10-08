package backend.server.PostWave.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "api/v1/post/all_posts",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST_URL).permitAll()
//                securing the whole endpoint
                .requestMatchers("api/v1/admin/**").hasRole("ADMIN")
//                securing the different operations
                .requestMatchers(HttpMethod.GET,"api/v1/admin/**").hasAuthority("ADMIN_READ")
                .requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasAuthority("ADMIN_CREATE")
                .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasAuthority("ADMIN_UPDATE")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasAuthority("ADMIN_DELETE")
//                .requestMatchers("/api/v1/user/currentUser").hasAnyRole("USER", "ADMIN")

                // Permissions pour les utilisateurs
                .requestMatchers("api/v1/user/**").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.GET, "api/v1/user/**").hasAuthority("USER_READ")
                .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasAuthority("USER_CREATE")
                .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAuthority("USER_UPDATE")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasRole("USER_DELETE")
                // Permissions pour les posts
                .requestMatchers("api/v1/post/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "api/v1/post/**").hasAuthority("USER_READ")
                .requestMatchers(HttpMethod.POST, "/api/v1/post/**").hasAuthority("USER_CREATE")
                .requestMatchers(HttpMethod.PUT, "/api/v1/post/**").hasAuthority("USER_UPDATE")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/post/**").hasAuthority("USER_DELETE")


                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
