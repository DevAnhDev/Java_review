package com.example.validatedocsapi.controller;

import com.example.validatedocsapi.dto.response.ResponseData;
import com.example.validatedocsapi.dto.response.ResponseError;
import com.example.validatedocsapi.dto.response.ResponseSuccess;
import com.example.validatedocsapi.entity.Users;
import com.example.validatedocsapi.service.implementService.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Validated
@Tag(name="User Controller")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UsersService usersService;

    @Operation(summary = "Get all user", description = "API get all user!")
    @GetMapping
    public ResponseData<List<Users>> getUsers() {
        List<Users> users = usersService.findAllUsers();
        return new ResponseData(HttpStatus.OK.value(), "List Users", users);
    }

    @GetMapping("/list")
    public ResponseData<List<Users>> getUsersList(@RequestParam(defaultValue = "0", required = false) int pageNo, @Min(10) @RequestParam(defaultValue = "10", required = false) int pageSize) {
        List<Users> users = usersService.findAllUsers();
        return new ResponseData(HttpStatus.OK.value(), "List Users", users);
    }

    @GetMapping("/{idUser}")
    public ResponseData<Users> getUserById(@Min(value = 1, message = "userId min is 1!") @PathVariable Long idUser) {
//        dung ex maf kg su dung controllerexceptionadvide
        try {
            Users user = usersService.findUsersById(idUser);
            return new ResponseData(HttpStatus.OK.value(), "User By Id " + idUser + " found", user);
        }catch(EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(),"loi roi");
            //service phai su dung throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }
    @Operation(summary = "Add new user", description = "send request new user create new user")
    @PostMapping
    public ResponseData<Integer> insertData(@Valid @RequestBody Users user) {

        usersService.save(user);
        return new ResponseData(HttpStatus.CREATED.value(), "User Inserted Successfully", user);
    }



    @Operation(summary = "sumary", description = "Update user.", responses = {
            @ApiResponse(responseCode = "202", description = "User updated successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(name = "update user", summary = "update user",
            value = """
                      {
                      "status": 202,
                      "message": "User Updated Successfully",
                      "data": {
                        "id": 4,
                        "userName": "Trinh",
                        "age": 15,
                        "email": "tring@gmail.com",
                        "phoneNumber": "01233435"
                      }
                    }
                    """)))
    })
    @PutMapping("/{userId}")
    public ResponseSuccess updateData(@PathVariable Long userId,@Valid @RequestBody Users user) {
        usersService.updateUsers(userId, user);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "User Updated Successfully", user);
    }

    @DeleteMapping("/{userId}")
    public ResponseSuccess deleteData(@PathVariable Long userId) {
        usersService.deleteUser(userId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "User Deleted Successfully");
    }






}
