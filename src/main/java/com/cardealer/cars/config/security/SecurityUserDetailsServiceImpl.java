package com.cardealer.cars.config.security;

import com.cardealer.cars.model.entity.UserDetails;
import com.cardealer.cars.service.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityUserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserDetailsService userDetailsService;

  public SecurityUserDetailsServiceImpl(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }


  @Override
  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDetails userDetails = userDetailsService.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("User with email " + email + " was not found."));
    return mapToUserDetails(userDetails);
  }


  private org.springframework.security.core.userdetails.UserDetails mapToUserDetails(UserDetails userDetails) {
    List<SimpleGrantedAuthority> authorities = userDetailsService.getById(userDetails.getId()).getPlayer().
        getRoles().
        stream().
        map(pr -> new SimpleGrantedAuthority("ROLE_" + pr.getRole().name())).
        collect(Collectors.toList());

    return new User(
            userDetails.getEmail(),
            userDetails.getPassword(),
        authorities
    );
  }
}
