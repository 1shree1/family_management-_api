package com.Shree.FamilyManagement.repository;

import com.Shree.FamilyManagement.entity.Family;
import com.Shree.FamilyManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    List<Family> findAllByUsers(Users users);
}
