package com.spring.SpringApp.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping(path = "/re")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @PostMapping(path="login")
    public ResponseEntity<String> login(@RequestBody RegistrationRequest request) {
        return registrationService.login(request);
    }

    @GetMapping (path = "confirm")
    public String confirmToken(@RequestParam("token") String value) {
        return registrationService.confirmToken(value);
    }
}
