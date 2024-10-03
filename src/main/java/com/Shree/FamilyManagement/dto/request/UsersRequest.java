package com.Shree.FamilyManagement.dto.request;

import lombok.Data;

@Data

public class UsersRequest {
    private String name;
    private String phoneNumber;
    private String address;
    private int age;
    private String gender;
    private String password;
    private String email;


}
