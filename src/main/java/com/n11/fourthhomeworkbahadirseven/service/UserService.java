package com.n11.fourthhomeworkbahadirseven.service;

import com.n11.fourthhomeworkbahadirseven.constant.ExceptionConstant;
import com.n11.fourthhomeworkbahadirseven.dto.user.UserCreateRequestDTO;
import com.n11.fourthhomeworkbahadirseven.dto.user.UserResponseDTO;
import com.n11.fourthhomeworkbahadirseven.exception.EntityNotFoundException;
import com.n11.fourthhomeworkbahadirseven.model.User;
import com.n11.fourthhomeworkbahadirseven.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO saveUser(UserCreateRequestDTO userRequest) {
        User user = User.createNewUserFromUserRequest(userRequest);

        User savedUser = userRepository.save(user);
        return UserResponseDTO.fromUser(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ExceptionConstant.USER_NOT_FOUND.getValue(), id)));
        return UserResponseDTO.fromUser(user);
    }
}
