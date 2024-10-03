package com.Shree.FamilyManagement.controller;

import com.Shree.FamilyManagement.dto.request.FamilyRequest;
import com.Shree.FamilyManagement.dto.request.FamilyUpdateRequest;
import com.Shree.FamilyManagement.dto.response.FamilyResponse;
import com.Shree.FamilyManagement.exception.UserNotFoundException;
import com.Shree.FamilyManagement.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/family")
@RequiredArgsConstructor
@Validated

public class FamilyController {
    private final FamilyService familyService;
    @PostMapping("/add")
    public ResponseEntity<List<FamilyResponse>> addFamily(@RequestBody List<FamilyRequest> familyRequest) {
        return ResponseEntity.ok(familyService.saveFamily(familyRequest));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<FamilyResponse>> getAllFamilies(@PathVariable Long userId) {
        return ResponseEntity.ok(familyService.getAllFamilyByUserId(userId));
    }


    @GetMapping("/getById/{familyId}")
    public ResponseEntity<FamilyResponse> getFamilyById(@PathVariable Long familyId) {
        return ResponseEntity.ok(familyService.getFamilyById(familyId));
    }

    @PutMapping("/update/{familyId}")
    public ResponseEntity<FamilyResponse> updateFamily(@PathVariable Long familyId, @RequestBody FamilyUpdateRequest familyUpdateRequest) {
        return ResponseEntity.ok(familyService.updateFamilyById(familyId, familyUpdateRequest));
    }

    @DeleteMapping("/delete/{familyId}")
    public ResponseEntity<String> deleteFamily(@PathVariable Long familyId) {
        return ResponseEntity.ok(familyService.deleteFamilyById(familyId));
    }
}
