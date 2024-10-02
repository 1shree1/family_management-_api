package com.Shree.FamilyManagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tbl_family")
@AllArgsConstructor
@NoArgsConstructor
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;
    private String name;
    private String relation;

    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String address;
    private int age;
    private String gender;

    @ManyToOne
    private Users users;
}
