package com.Shree.FamilyManagement.service.impl;

import com.Shree.FamilyManagement.dto.request.LoginRequest;
import com.Shree.FamilyManagement.dto.request.UsersRequest;
import com.Shree.FamilyManagement.dto.request.UsersUpdateRequest;
import com.Shree.FamilyManagement.dto.response.UsersResponse;
import com.Shree.FamilyManagement.dto.response.LoginResponse;
import com.Shree.FamilyManagement.entity.Family;
import com.Shree.FamilyManagement.entity.Users;
import com.Shree.FamilyManagement.repository.FamilyRepository;
import com.Shree.FamilyManagement.repository.UsersRepository;
import com.Shree.FamilyManagement.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImplementation implements UsersService {

    private final UsersRepository usersRepository;
    private final FamilyRepository familyRepository;

    @Override
    public UsersResponse saveUser(UsersRequest usersRequest){
        log.info("Saving user details: {}", usersRequest);
        Users users = new Users();
        users.setName(usersRequest.getName());
        users.setAddress(usersRequest.getAddress());
        users.setAge(usersRequest.getAge());
        users.setPhoneNo(usersRequest.getPhoneNumber());
        users.setGender(usersRequest.getGender());
        users.setPassword(usersRequest.getPassword());
        users.setEmail(usersRequest.getEmail());
        return new UsersResponse(usersRepository.save(users));
    }

    @Override
    public UsersResponse getUserById(Long userId) {
        log.info("User get request received");
        Users users = usersRepository.findById(userId)
                .orElseThrow(
                        ()-> new EntityNotFoundException("User not found")
                );
        log.info("User data fetch successfully");
        return new UsersResponse(users);
    }

    @Override
    public List<UsersResponse> getAllUserByUserId(Long userId) {
        return null;
    }

    @Override
    public UsersResponse updateUserById(Long userId, UsersUpdateRequest userUpdateRequest) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        users.setName(userUpdateRequest.getName());
        users.setAddress(userUpdateRequest.getAddress());
        users.setAge(userUpdateRequest.getAge());
        users.setPhoneNo(userUpdateRequest.getPhoneNumber());
        users.setGender(userUpdateRequest.getGender());


        Users updatedUser = usersRepository.save(users);
        return new UsersResponse(updatedUser);
    }


    @Override
    public String deleteUserById(Long usersId) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(()-> new EntityNotFoundException("User not found!"));
        List<Family> families = familyRepository.findAllByUsers(users);
        familyRepository.deleteAll(families);
        usersRepository.delete(users);
        return "User deleted successfully!";
    }

    @Override
    public LoginResponse login (LoginRequest loginRequest){
        Users users  = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("Invalid User name or password"));
        if (loginRequest.getPassword().equals(users.getPassword())) {
            return new LoginResponse(users);
        } else {
            throw new RuntimeException("Invalid User name or password");
        }
    }

}
