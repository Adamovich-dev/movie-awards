package com.adamovichdev.movieawards.security;

import com.adamovichdev.movieawards.dao.entity.UserEntity;
import com.adamovichdev.movieawards.dao.repository.UserRepository;
import com.adamovichdev.movieawards.security.dto.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    //todo authorities
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUserName(username);
        if (user != null) {
            return new CustomUser(user.getUserName(), user.getId(), user.getPassword(), new ArrayList<>());
        } else {
            //todo message
            throw new UsernameNotFoundException("");
        }
    }
}
