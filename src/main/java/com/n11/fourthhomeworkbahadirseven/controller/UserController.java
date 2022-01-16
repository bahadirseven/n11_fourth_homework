package com.n11.fourthhomeworkbahadirseven.controller;

import com.n11.fourthhomeworkbahadirseven.constant.ResponseConstant;
import com.n11.fourthhomeworkbahadirseven.dto.user.UserCreateRequestDTO;
import com.n11.fourthhomeworkbahadirseven.dto.user.UserResponseDTO;
import com.n11.fourthhomeworkbahadirseven.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserCreateRequestDTO userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().body(ResponseConstant.USER_DELETE_SUCCESS_MESSAGE.getValue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id){
        UserResponseDTO userResponse = userService.getUserById(id);
        return ResponseEntity.ok().body(userResponse);
    }
}
