package com.example.ecommers.service;
import com.example.ecommers.exception.CustomHandler;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.repository.I_UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This service class provides user details and authentication information for Spring Security.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailServerImpl implements UserDetailsService {


    private final I_UserRepository userRepository;

    /**
     * Loads user details by username for authentication and authorization.
     *
     * @param email The username of the user to be loaded.
     * @return User details containing authentication and authorization information.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {

            UserEntity userEntity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

            Collection<? extends GrantedAuthority> authorities = userEntity.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                    .collect(Collectors.toSet());

            return new User(
                    userEntity.getUserName(),
                    userEntity.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorities
            );
        } catch (UsernameNotFoundException e) {
            System.out.println("Error accessing user database: User not found - " + email+ e);
            throw e;  // Propagate the original exception
        } catch (Exception e) {
            throw new CustomHandler("Error accessing user database");
        }
    }
}