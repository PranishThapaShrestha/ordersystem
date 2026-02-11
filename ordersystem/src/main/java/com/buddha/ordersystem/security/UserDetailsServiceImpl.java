package com.buddha.ordersystem.security;

import com.buddha.ordersystem.entity.Users;
import com.buddha.ordersystem.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Users user = userRepository.findBdByEmail(userEmail).
                orElseThrow(() -> new UsernameNotFoundException("User not found of" + userEmail));
        return new UserDetailsImpl(user);
    }
}
