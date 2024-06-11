package com.gustavo.parkingcontrolapi.service;

import com.gustavo.parkingcontrolapi.enums.RoleName;
import com.gustavo.parkingcontrolapi.model.RoleModel;
import com.gustavo.parkingcontrolapi.model.UserModel;
import com.gustavo.parkingcontrolapi.repository.RoleModelRepository;
import com.gustavo.parkingcontrolapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleModelRepository roleModelRepository;

    public List<UserModel> getUsers(){
        return userRepository.findAll();
    }
    public Optional<UserModel> getOneUserById(UUID id){
        return userRepository.findById(id);
    }
    public Optional<UserModel> getOneUserByName(String username){
        return userRepository.findByUsername(username);
    }
    public UserModel createUser(UserModel userModel) {
        List<RoleModel> roleModels = new ArrayList<>();

        for (RoleModel roleModel : userModel.getRoleModels()) {
            RoleModel existingRole = roleModelRepository.findByRoleName(roleModel.getRoleName());
            if (existingRole != null) {
                roleModels.add(existingRole);
            } else {
                roleModels.add(roleModel);
            }
        }

        userModel.setRoleModels(roleModels);
        return userRepository.save(userModel);
    }
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }
}
