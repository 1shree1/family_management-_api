package com.Shree.FamilyManagement.service;
import com.Shree.FamilyManagement.dto.request.LoginRequest;
import com.Shree.FamilyManagement.dto.request.UsersRequest;
import com.Shree.FamilyManagement.dto.request.UsersUpdateRequest;
import com.Shree.FamilyManagement.dto.response.UsersResponse;
import com.Shree.FamilyManagement.dto.response.LoginResponse;
import com.Shree.FamilyManagement.exception.UserNotFoundException;
import java.util.List;

public interface UsersService {

    UsersResponse saveUser(UsersRequest userRequest) throws UserNotFoundException;
    UsersResponse getUserById(Long userId);
    List<UsersResponse> getAllUserByUserId(Long userId);
    UsersResponse updateUserById(Long userId, UsersUpdateRequest userUpdateRequest);
    String deleteUserById(Long userId);

    LoginResponse login (LoginRequest loginRequest);
}
