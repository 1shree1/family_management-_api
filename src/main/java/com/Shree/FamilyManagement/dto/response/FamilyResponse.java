package com.Shree.FamilyManagement.dto.response;
import com.Shree.FamilyManagement.entity.Family;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class FamilyResponse {

    private Long id;
    private String name;
    private String relation;
    private String phoneNumber;
    private String address;
    private int age;
    private String gender;

    public FamilyResponse(Family family){
        this.id = family.getFamilyId();
        this.name = family.getName();
        this.relation = family.getRelation();
        this.phoneNumber = family.getPhoneNumber();
        this.address = family.getAddress();
        this.age = family.getAge();
        this.gender = family.getGender();
    }
}
