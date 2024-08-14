package com.example.validatedocsapi.service.InterfaceService;
import com.example.validatedocsapi.dto.request.UserRequestDTO;
import com.example.validatedocsapi.dto.response.UserDetailResponse;
import com.example.validatedocsapi.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {

    Long saveUser(UserRequestDTO userRequestDTO);

    void updateUser(long userId, UserRequestDTO userRequestDTO);

    void deleteUser(long userId);

    List<UserRequestDTO> getAll();

    UserDetailResponse getUser(long userId);

    List<UserDetailResponse> getAllUsers(int pageNo, int pageSize);
}
