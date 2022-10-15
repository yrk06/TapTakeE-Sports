package com.taptake.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.taptake.backend.model.DefaultUserDetails;
import com.taptake.backend.model.User;

public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("The requested username was not found");
        }
        DefaultUserDetails ud = new DefaultUserDetails();
        ud.setUsername(username);
        ud.setPassword(user.get().getSenha());
        return ud;
    }

}
