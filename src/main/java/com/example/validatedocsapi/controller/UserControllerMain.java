package com.example.validatedocsapi.controller;

import com.example.validatedocsapi.dto.request.UserRequestDTO;
import com.example.validatedocsapi.dto.response.ResponseData;
import com.example.validatedocsapi.dto.response.ResponseError;
import com.example.validatedocsapi.entity.Users;
import com.example.validatedocsapi.exception.ResourceNotFoundException;
import com.example.validatedocsapi.service.InterfaceService.UserService;
import com.example.validatedocsapi.service.implementService.UsersService1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("api/v2/users")
@Slf4j
@Validated
@Tag(name="New User Controller")
@RequiredArgsConstructor

public class UserControllerMain {
    @Autowired
    private  UsersService1 userService;

    @Operation(method= "POST",summary = "add new user", description = "reuqest send user create user")
    @PostMapping
    public ResponseData<Long> addUser(@Valid @RequestBody UserRequestDTO user){
        log.info("adding new user {} {}", user.getUserName(), user.getEmail());
        try {
            long idUser = userService.saveUser(user);
            return new ResponseData<>(HttpStatus.OK.value(), "add user successful", idUser);
        }catch (Exception e){
            log.error("error message={} {} ",e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add user fail");
        }
    }

    @Operation(summary = "update user.", description = "update user with user ID.")
    @PutMapping("/{userId}")
    public ResponseData<UserRequestDTO> updateUser(@Valid @PathVariable long userId, @RequestBody UserRequestDTO user){
        log.info("updating user {} {}", user.getUserName(), user.getEmail());
        try {
            userService.updateUser(userId, user);
            return new ResponseData<>(HttpStatus.OK.value(), "update user successful", user);
        }catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }

    @Operation(summary = "get all user", description = "get all user")
    @GetMapping
    public ResponseData<List<UserRequestDTO>> getAllUser(){
        log.info("getting all users");
        List<UserRequestDTO> user = userService.getAll();
        return new ResponseData<>(HttpStatus.OK.value(), "get all users successful", user );
    }



}
