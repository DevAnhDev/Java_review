package com.example.validatedocsapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class Users extends AbstractEntity {


    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Address> addresses = new HashSet<>();

    public void addAddress(Address address) {
        if(address != null) {
            if(addresses == null){
                addresses = new HashSet<>();
            }
            addresses.add(address);
            address.setUsers(this);
        }
    }


}
