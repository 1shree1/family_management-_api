package com.Shree.FamilyManagement.service.impl;

import com.Shree.FamilyManagement.dto.request.FamilyRequest;
import com.Shree.FamilyManagement.dto.request.FamilyUpdateRequest;
import com.Shree.FamilyManagement.dto.response.FamilyResponse;
import com.Shree.FamilyManagement.entity.Family;
import com.Shree.FamilyManagement.entity.Users;
import com.Shree.FamilyManagement.exception.UserNotFoundException;
import com.Shree.FamilyManagement.repository.FamilyRepository;
import com.Shree.FamilyManagement.repository.UsersRepository;
import com.Shree.FamilyManagement.service.FamilyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {
    private final FamilyRepository familyRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<FamilyResponse> saveFamily(List<FamilyRequest> familyRequest) {
        List<FamilyResponse> familyResponseList = new ArrayList<>();
        for (FamilyRequest request : familyRequest) {


            Family family = new Family();
            family.setName(request.getName());
            family.setAddress(request.getAddress());
            family.setRelation(request.getRelation());
            family.setAge(request.getAge());
            family.setPhoneNumber(request.getPhoneNumber());
            family.setGender(request.getGender());
            Users users = usersRepository.findById(request.getUserId()).orElseThrow(
                    () -> new EntityNotFoundException("User Not Found!")
            );
            family.setUsers(users);
            Family savedFamily = familyRepository.save(family);
            FamilyResponse familyResponse = new FamilyResponse(savedFamily);
            familyResponseList.add(familyResponse);
        }

        return familyResponseList;
    }

    @Override
    public FamilyResponse getFamilyById(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Family not found")
                );
        return new FamilyResponse(family);
    }

    @Override
    public List<FamilyResponse> getAllFamilyByUserId(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(
                        ()-> new EntityNotFoundException("User not found")
                );
        List<Family> families = familyRepository.findAllByUsers(user);
        List<FamilyResponse> familyResponseList = new ArrayList<>();
        for (int i = 0; i<families.size(); i++) {
            familyResponseList.add(new FamilyResponse(families.get(i)));
        }
        return familyResponseList;
    }

    @Override
    public FamilyResponse updateFamilyById(Long familyId, FamilyUpdateRequest familyUpdateRequest) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(()-> new EntityNotFoundException("Family not found"));
        family.setName(familyUpdateRequest.getName());
        family.setAddress(familyUpdateRequest.getAddress());
        family.setAge(familyUpdateRequest.getAge());
        family.setRelation(familyUpdateRequest.getRelation());
        family.setPhoneNumber(familyUpdateRequest.getPhoneNumber());
        family.setGender(familyUpdateRequest.getGender());

        Family updatedFamily = familyRepository.save(family);
        return new FamilyResponse(updatedFamily);
    }

    @Override
    public String deleteFamilyById(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(()-> new EntityNotFoundException("Family not found!"));
        familyRepository.delete(family);
        return "Family deleted successfully!";
    }
}
