package com.Shree.FamilyManagement.controller;

import com.Shree.FamilyManagement.dto.request.LoginRequest;
import com.Shree.FamilyManagement.dto.response.LoginResponse;
import com.Shree.FamilyManagement.service.UsersService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(usersService.login(loginRequest));
    }


}


