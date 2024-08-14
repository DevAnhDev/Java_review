package com.example.validatedocsapi.dto.request;

import com.example.validatedocsapi.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class AddressRequestDTO implements Serializable {
    private Long id;
    private String street;
    private String city;
    private String state;


    public AddressRequestDTO(Long id, String street, String city, String state) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;

    }
}
