package com.bdpanajoto.ws.rest.security;

import com.bdpanajoto.ws.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        com.bdpanajoto.ws.rest.domain.User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with specified username"));

        List<String> role = userEntity.getGroups().stream()
                .map(group -> group.getName()).collect(Collectors.toList());

        return User.withDefaultPasswordEncoder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(role.toArray(new String[]{}))
                .build();
    }

}