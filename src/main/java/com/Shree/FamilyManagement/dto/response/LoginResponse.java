package com.Shree.FamilyManagement.dto.response;

import com.Shree.FamilyManagement.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private long userId;
    private String name;
    private boolean isSuccessful = false;

    public LoginResponse(Users users) {
        this.userId = users.getUserId();
        this.name= users.getName();
        this.isSuccessful = true;
    }

}
