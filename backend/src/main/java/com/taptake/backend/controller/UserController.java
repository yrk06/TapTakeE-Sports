package com.taptake.backend.controller;

import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.model.User;
import com.taptake.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody UserDTO userDTO){
        if(userService.findByEmail(userDTO.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPoints(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<Object> exemplo(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestParam("email") String email){
        Optional<User> userOptional = userService.findByEmail(email);
        if(userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);

    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteOne(@RequestParam("email") String email){
        Optional<User> userOptional = userService.findByEmail(email);
        if(userOptional.isPresent()){
            userService.deleteOne(userOptional.get().getId());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

    }

    @PutMapping
    public ResponseEntity<Object> updateOne(@RequestBody UserDTO userDTO, @RequestParam("emal") String email){
        Optional<User> userOptional = userService.findByEmail(email);

        if(!userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        User newUser = userOptional.get();

        if(userDTO.getUsername()!=newUser.getUsername()){
            newUser.setUsername(userDTO.getUsername());
        }
        if(userDTO.getPhone()!=newUser.getPhone()){
            newUser.setPhone(userDTO.getPhone());
        }
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPoints(0);
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }









}
