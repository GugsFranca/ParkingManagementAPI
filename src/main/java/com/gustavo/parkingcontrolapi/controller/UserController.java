package com.gustavo.parkingcontrolapi.controller;

import com.gustavo.parkingcontrolapi.DTO.UserModelDto;
import com.gustavo.parkingcontrolapi.model.RoleModel;
import com.gustavo.parkingcontrolapi.model.UserModel;
import com.gustavo.parkingcontrolapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserModel>> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.getOneUserById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<UserModel>> getUserById(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getOneUserByName(username));
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserModelDto userModelDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModelDto, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
