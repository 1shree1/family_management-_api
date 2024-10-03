package com.Shree.FamilyManagement.service;
import com.Shree.FamilyManagement.dto.request.FamilyRequest;
import com.Shree.FamilyManagement.dto.request.FamilyUpdateRequest;
import com.Shree.FamilyManagement.dto.response.FamilyResponse;
import com.Shree.FamilyManagement.exception.UserNotFoundException;

import java.util.List;

public interface FamilyService {

    List<FamilyResponse> saveFamily(List<FamilyRequest> familyRequest);
    FamilyResponse getFamilyById(Long familyId);
    List<FamilyResponse> getAllFamilyByUserId(Long userId);
    FamilyResponse updateFamilyById(Long familyId, FamilyUpdateRequest familyUpdateRequest);
    String deleteFamilyById(Long familyId);

}
