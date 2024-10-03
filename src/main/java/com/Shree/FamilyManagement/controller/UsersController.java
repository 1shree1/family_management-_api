package com.Shree.FamilyManagement.controller;

import com.Shree.FamilyManagement.dto.request.LoginRequest;
import com.Shree.FamilyManagement.dto.request.UsersRequest;
import com.Shree.FamilyManagement.dto.request.UsersUpdateRequest;
import com.Shree.FamilyManagement.dto.response.UsersResponse;
import com.Shree.FamilyManagement.dto.response.LoginResponse;
import com.Shree.FamilyManagement.exception.UserNotFoundException;
import com.Shree.FamilyManagement.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
public class UsersController {

    private final UsersService userService;
    @PostMapping("/add")
    public ResponseEntity<UsersResponse> addUser(@RequestBody UsersRequest userRequest) throws UserNotFoundException {
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }


    @GetMapping("/getById/{userId}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UsersResponse> updateUser(@PathVariable Long userId, @RequestBody UsersUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.updateUserById(userId, userUpdateRequest));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }


}
