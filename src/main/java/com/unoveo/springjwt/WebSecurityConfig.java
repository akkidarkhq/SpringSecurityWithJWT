package com.unoveo.springjwt;


import com.unoveo.springjwt.models.Role;
import com.unoveo.springjwt.models.User;
import com.unoveo.springjwt.security.jwt.AuthEntryPointJwt;
import com.unoveo.springjwt.security.jwt.AuthTokenFilter;
import com.unoveo.springjwt.security.jwt.JwtUtils;
import com.unoveo.springjwt.security.services.UserDetailsServiceImpl;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;


///////////https://www.bezkoder.com/spring-boot-security-login-jwt/
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@ComponentScan("com.unoveo.springjwt.*")
@EntityScan(basePackageClasses = {User.class, Role.class})
public class WebSecurityConfig {
 @Autowired
  public UserDetailsServiceImpl userDetailsService ;
  @Autowired
  public AuthEntryPointJwt unauthorizedHandler;

  @Autowired AuthTokenFilter authTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//  public AuthTokenFilter authenticationJwtTokenFilter() {
//    return new AuthTokenFilter();
//  }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }


  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
  }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(
              auth->
              auth.requestMatchers("/login").permitAll()
                      .requestMatchers("/api/test/admin").hasRole("ADMIN")
                      .requestMatchers("/api/test/user").hasRole("USER")
                      .requestMatchers("/api/test/mod").hasRole("MODERATOR")
                      .requestMatchers("api/test/**").permitAll()
              .requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
      );
    http.authenticationProvider(authenticationProvider());
    http.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).logout(Customizer.withDefaults());
    http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }


//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.csrf(csrf -> csrf.disable())
//        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .authorizeHttpRequests(auth ->
//          auth.requestMatchers("/login").permitAll()
//                  .requestMatchers("/api/auth/**").permitAll()
//
//                  .requestMatchers("/api/test/user").hasAnyRole("USER")
//                .requestMatchers("/api/test/mod").hasAnyRole("MODERATOR")
//            .requestMatchers("/api/test/admin").hasAnyRole("ADMIN")
//
//              .anyRequest().authenticated()
//        ).formLogin(Customizer.withDefaults());
//    http.authenticationProvider(authenticationProvider());
//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//  }
}

//@GetMapping("/user")
////  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//
//@GetMapping("/mod")
////  @PreAuthorize("hasRole('MODERATOR')")
//
//@GetMapping("/admin")
////  @PreAuthorize("hasRole('ADMIN')")
