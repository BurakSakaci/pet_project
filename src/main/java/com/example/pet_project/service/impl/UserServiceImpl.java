package com.example.pet_project.service.impl;

import com.example.pet_project.model.dao.CityRepository;
import com.example.pet_project.model.dao.UserRepository;
import com.example.pet_project.model.dto.UserDTO;
import com.example.pet_project.model.dto.UserLoginDTO;
import com.example.pet_project.model.entities.City;
import com.example.pet_project.model.entities.User;
import com.example.pet_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        return repository.save(userDTOToUser(userDTO));
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(UserDTO user) {
        try {
            User existingUser = repository.getByUserName(user.getUserName());
            user.setId(existingUser.getId());
            User updatedUser = repository.save(userDTOToUser(user));
            return updatedUser;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Integer checkUserLogin(UserLoginDTO userLoginDTO) {
        try {
            return repository.checkUserLogin(userLoginDTO.getUserName(), userLoginDTO.getPassword());
//            User user = repository.getByUserName(userLoginDTO.getUserName());
//            if (user != null) {
//
//                if (user.getPassword().equals(userLoginDTO.getPassword())) {
//                    return 1; // success
//                } else {
//                    return 2; // invalid password
//                }
//            }
        } catch (Exception e) {
            return 3; // user not found
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        return repository.getByUserName(userName);
    }

    public UserDTO getUserDTOById(Long id) {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setPassword(user.getPassword());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setAddress(user.getAddress());
            if (user.getCity() != null) {
                userDTO.setCityId(user.getCity().getCityCode().toString());
            }
            return userDTO;
        }
        return null;
    }

    // Other service methods...

    // Optionally, you can create a method to map from UserDTO to User.
    public User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());

        // You'll need to fetch the City entity by cityId and set it here
        if (userDTO.getCityId() != null) {
            Long cityCode = Long.parseLong(userDTO.getCityId());
            City city = cityRepository.findById(cityCode).orElse(null);
            user.setCity(city);
        }

        return user;
    }

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAddress(user.getAddress());

        if (user.getCity() != null) {
            userDTO.setCityId(String.valueOf(user.getCity().getId()));
        }

        return userDTO;
    }
}
