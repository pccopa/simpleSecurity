package org.edit.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.edit.shop.config.UserPrincipal;
import org.edit.shop.entity.User;
import org.edit.shop.repository.UserRepository;
import org.edit.shop.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;

    /**
     * Cargamos info del usuario y creamos un UserPrincipal para guardarlo en el contexto
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(user.getUsername(), user.getPassword(), user.getId(), authorities);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
