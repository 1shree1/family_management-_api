package com.Shree.FamilyManagement.dto.response;

import com.Shree.FamilyManagement.entity.Family;
import com.Shree.FamilyManagement.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class UsersResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private int age;
    private String gender;
    private String email;

    public UsersResponse(Users users){
        this.id = users.getUserId();
        this.name = users.getName();
        this.phoneNumber = users.getPhoneNo();
        this.address = users.getAddress();
        this.age = users.getAge();
        this.gender = users.getGender();
        this.email = users.getEmail();
    }

}
