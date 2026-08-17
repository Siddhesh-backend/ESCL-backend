package com.escl.service;

import com.escl.model.PlayerRegistration;
import com.escl.repository.PlayerRegistrationRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PlayerRegistrationRepository repo;

    public CustomUserDetailsService(PlayerRegistrationRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        PlayerRegistration player = repo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(player.getEmail())
                .password(player.getPassword())
                .authorities(
                        new SimpleGrantedAuthority(
                                "ROLE_" + player.getRole()
                        )
                )
                .build();
    }
}
