package com.spring.SpringApp.registration;

import com.spring.SpringApp.appuser.AppUser;
import com.spring.SpringApp.appuser.AppUserRole;
import com.spring.SpringApp.appuser.AppUserService;
import com.spring.SpringApp.email.EmailSender;

import com.spring.SpringApp.token.Token;
import com.spring.SpringApp.token.TokenService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AuthenticationManager authenticationManager;
    private EmailValidator emailValidator;
    private AppUserService appUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenService tokenService;
    private EmailSender emailSender;
    private final com.spring.SpringApp.security.JwtUtils jwtUtils;

    public ResponseEntity<String> login(RegistrationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));
        final AppUser appUser = (AppUser) appUserService.loadUserByUsername(request.getEmail());
        if (appUser != null) {
           return ResponseEntity.ok(jwtUtils.generateToken(appUser));
        }
        return ResponseEntity.status(400).body("Error");
    }

    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email is not valid");
        }

        String value = appUserService.signUpUser(
                new AppUser(
                        request.getName(),
                        request.getPassword(),
                        request.getEmail(),
                        AppUserRole.ROLE_USER
                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + value;
        //emailSender.send(request.getEmail(), buildMessage(request.getName(), link));

        return "Registration done!";
    }

    @Transactional
    public String confirmToken(String value) {
        Token token = tokenService.getToken(value).orElseThrow(
                () -> new IllegalStateException("Token is not found")
        );

        if (token.getConfirmedAt() != null) {
            throw new IllegalStateException("Token is already confirmed");
        }

        LocalDateTime expiresAt = token.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is expired");
        }
        else {
            token.setConfirmedAt(LocalDateTime.now());
            tokenService.saveToken(token);
            appUserService.enableAppUser(token.getUser().getEmail());
        }
        return "confirmed";
    }

    public String buildMessage(String name, String link) {
        return "<div>" +
                "<div>Hello, " + name + ".</div>" +
                "<br><br>" +
                "<a href=\"" + link + "\">Please, click to confirm your token!</a>" +
                "</div>";
    }
}
