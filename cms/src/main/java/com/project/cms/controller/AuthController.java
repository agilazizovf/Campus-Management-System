package com.project.cms.controller;


import com.project.cms.dto.request.AuthRequest;
import com.project.cms.exception.CustomException;
import com.project.cms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginReq, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        return authService.login(loginReq);
    }
}
