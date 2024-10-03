package com.Shree.FamilyManagement.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String phoneNo;
    private String Address;
    private int age;
    private String gender;
    private String password;

    @Column(unique = true)
    private String email;
}
