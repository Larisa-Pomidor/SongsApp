package com.spring.SpringApp.appuser;

import com.spring.SpringApp.token.Token;
import com.spring.SpringApp.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean emailExists = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if (!emailExists) {
            appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            // appUser.setPassword(appUser.getPassword());
            appUserRepository.save(appUser);
        }
        else {
            appUser = appUserRepository.findByEmail(appUser.getEmail()).orElseThrow(() ->
                    new IllegalStateException("User is not found"));
        }

        String value = UUID.randomUUID().toString();
        Token token = new Token(
                value,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        tokenService.saveToken(token);
        return value;
    }

    public void enableAppUser(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User is not found"));
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
    }
}
