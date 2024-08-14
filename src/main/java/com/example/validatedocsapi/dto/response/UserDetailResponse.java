package com.example.validatedocsapi.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class UserDetailResponse implements Serializable {
    private int age;
    private String email;
    private String phoneNumber;
    private String userName;
}
