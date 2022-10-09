package com.taptake.backend.controller;

import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.model.User;
import com.taptake.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody UserDTO userDTO) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        if (userService.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setSenha(bc.encode(user.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestParam("email") String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping
    public ResponseEntity<Object> updateOne(@RequestBody UserDTO userDTO, @RequestParam("email") String email) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        Optional<User> userOptional = userService.findByEmail(email);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User newUser = userOptional.get();

        if (!userDTO.getNome().equals(newUser.getNome())) {
            newUser.setNome(userDTO.getNome());
        }
        if (!userDTO.getTelefone().equals(newUser.getTelefone())) {
            newUser.setTelefone(userDTO.getTelefone());
        }
        if (!bc.matches(userDTO.getSenha(), newUser.getSenha())) {
            newUser.setSenha(bc.encode(userDTO.getSenha()));
        }
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteOne(@RequestParam("email") String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            userService.deleteOne(userOptional.get().getIdUsuario());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
