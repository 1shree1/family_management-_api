package com.Shree.FamilyManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyUpdateRequest {
    private String name;
    private String relation;
    private String phoneNumber;
    private String address;
    private int age;
    private String  gender;
}

