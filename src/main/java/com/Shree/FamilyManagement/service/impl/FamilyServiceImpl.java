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
    public FamilyResponse saveFamily(FamilyRequest familyRequest) throws UserNotFoundException {
        log.info("Saving family details: {}", familyRequest);
        Family family = new Family();
        family.setName(familyRequest.getName());
        family.setAddress(familyRequest.getAddress());
        family.setRelation(familyRequest.getRelation());
        family.setAge(familyRequest.getAge());
        family.setPhoneNumber(familyRequest.getPhoneNumber());
        family.setGender(familyRequest.getGender());

        Users users = usersRepository.findById(familyRequest.getUserId()).orElseThrow(
                ()-> new UserNotFoundException("User Not Found!")
        );
        family.setUsers(users);
        Family savedFamily = familyRepository.save(family);
        log.info("Family Saved: {}", family);
        return new FamilyResponse(savedFamily);
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
