package com.example.validatedocsapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="address")
public class Address extends AbstractEntity {

    @Column(name = "street")
    private String street;

    @NotBlank(message = "city no null!")
    @Column(name = "city" )
    private String city;

    @NotBlank(message = "state no null!")
    @Column(name = "state" )
    private String state;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;




}
