package com.cardealer.cars.config.security;


import com.cardealer.cars.config.security.jwt.JwtEmailAndPasswordAuthenticationFilter;
import com.cardealer.cars.config.security.jwt.JwtTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final SecurityUserDetailsServiceImpl securityUserDetailsServiceImpl;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityConfig(SecurityUserDetailsServiceImpl securityUserDetailsServiceImpl,
                        PasswordEncoder passwordEncoder) {
    this.securityUserDetailsServiceImpl = securityUserDetailsServiceImpl;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }


  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(securityUserDetailsServiceImpl);
    return provider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .cors().and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtEmailAndPasswordAuthenticationFilter(authenticationManager()))
            .addFilterAfter(new JwtTokenVerifier(), JwtEmailAndPasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers("/buycar/**", "deletecar/**", "/boughtcars/**", "/carinfo/**", "/myprofile/**",
                    "/search").hasAnyAuthority("ROLE_USER")
            .anyRequest()
            .authenticated();

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
            .antMatchers(HttpMethod.GET,"/cars", "/shop", "/confirm")
            .antMatchers(HttpMethod.POST, "/register", "/signin");
  }

}
