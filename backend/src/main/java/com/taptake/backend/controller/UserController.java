package com.taptake.backend.controller;

import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.model.User;
import com.taptake.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody UserDTO userDTO){
//        if(userService.existsByEmail(userDTO.getEmail())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
//        }
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPoints(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<Object> exemplo(){
        return ResponseEntity.status(HttpStatus.OK).body("ROGERIO CENI");
    }




}
