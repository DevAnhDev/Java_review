package com.example.validatedocsapi.dto.request;

import com.example.validatedocsapi.entity.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
public class UserRequestDTO implements Serializable {
    private Long id;

    @Min(18)
    @Max(100)
    @Column(name="age")
    private int age;

    @Email(message = "Email invalidate!")
    @Column(name="email")
    private String email;

    @Pattern(regexp ="^0\\d{9}$", message = "phone invalidate format!")
    @Column(name="phone_number")
    private String phoneNumber;

    @NotBlank(message = "Users Name not blank")
    @Column(name="user_name")
    private String userName;

    @NotBlank(message = "Password not blank")
    @Column(name="password")
    private String password;

    @Column(name="status")
    private String status;

    @NotNull(message="address not null")
    private Set<AddressRequestDTO> addresses;

    public UserRequestDTO(Long id , int age, String email, String phoneNumber, String userName, String password, String status, Set<AddressRequestDTO> addresses) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.addresses = addresses;
    }
}
