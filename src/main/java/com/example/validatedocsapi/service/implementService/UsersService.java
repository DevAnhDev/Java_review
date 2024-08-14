package com.example.validatedocsapi.service.implementService;

import com.example.validatedocsapi.entity.Users;
import com.example.validatedocsapi.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users findUsersById(Long id) {
        return usersRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User with id " + id + " not found"));
    }

    public Users updateUsers(Long id,Users user) {
        Users existingUser = findUsersById(id);
            if (existingUser != null) {
                existingUser.setUserName(user.getUserName());
                existingUser.setEmail(user.getEmail());
                existingUser.setAge(user.getAge());
                existingUser.setPhoneNumber(user.getPhoneNumber());
        }
        return usersRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }


}
