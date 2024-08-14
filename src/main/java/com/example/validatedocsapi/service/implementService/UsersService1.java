package com.example.validatedocsapi.service.implementService;

import com.example.validatedocsapi.dto.request.AddressRequestDTO;
import com.example.validatedocsapi.dto.request.UserRequestDTO;
import com.example.validatedocsapi.dto.response.UserDetailResponse;
import com.example.validatedocsapi.entity.Address;
import com.example.validatedocsapi.entity.Users;
import com.example.validatedocsapi.exception.ResourceNotFoundException;
import com.example.validatedocsapi.repository.UsersRepository;
import com.example.validatedocsapi.service.InterfaceService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService1 implements UserService {


    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public Long saveUser(UserRequestDTO request) {
        Users user = Users.builder()
                .age(request.getAge())
                .userName(request.getUserName())
                .email(request.getEmail())
                .status(request.getStatus())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();

        request.getAddresses().forEach(a->
                user.addAddress(Address.builder()
                        .street(a.getStreet())
                        .state(a.getState())
                        .city(a.getCity())
                .build()));
        usersRepository.save(user);
        log.info("User saved: {}", user);
        return user.getId();
    }



    @Override
    public void updateUser(long userId, UserRequestDTO userRequestDTO) {
        Users user = getUserById(userId);
        user.setUserName(userRequestDTO.getUserName());
        if(!userRequestDTO.getEmail().equals(user.getEmail())){
            user.setEmail(userRequestDTO.getEmail());
        }
        user.setEmail(userRequestDTO.getEmail());
        user.setStatus(userRequestDTO.getStatus());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());

        Set<Address> addresses = userRequestDTO.getAddresses().stream()
                .map(dto -> new Address(dto.getStreet(), dto.getCity(), dto.getState(), user))
                .collect(Collectors.toSet());

        user.setAddresses(addresses);
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        Users user = getUserById(userId);
        usersRepository.delete(user);
    }

    @Override
    public  List<UserRequestDTO>  getAll() {
        return usersRepository.findAll().stream()
                .map(this::convertToDTO) // Chuyển đổi từ User entity sang UserRequestDTO
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailResponse getUser(long userId) {
        return null;
    }

    @Override
    public List<UserDetailResponse> getAllUsers(int pageNo, int pageSize) {
        return List.of();
    }

    private Users getUserById(long userId){
        return usersRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found user id = "+userId));
    }


    private Set<Address> convertToAddress(Set<AddressRequestDTO> address){
        Set<Address> addressResult = new HashSet<>();
        address.forEach(a->
                addressResult.add(Address.builder()
                        .street(a.getStreet())
                        .city(a.getCity())
                        .state(a.getState())
                        .build())
                );
        return addressResult;
    }
private UserRequestDTO convertToDTO(Users user) {
        Set<AddressRequestDTO> addressDTOs = user.getAddresses().stream()
                .map(address -> new AddressRequestDTO(
                        address.getId(),
                        address.getStreet(),
                        address.getCity(),
                        address.getState()))
                .collect(Collectors.toSet());

        return new UserRequestDTO(
                user.getId(),
                user.getAge(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserName(),
                user.getPassword(),
                user.getStatus(),
                addressDTOs
        );
    }



}


