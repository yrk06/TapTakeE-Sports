package com.taptake.backend.controller;

import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.model.Role;
import com.taptake.backend.model.User;
import com.taptake.backend.service.RoleService;
import com.taptake.backend.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Object> savePerson(HttpServletRequest request, @RequestBody UserDTO userDTO) {
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Pattern pPassword = Pattern
                .compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,32}$");
        Pattern pPhone = Pattern.compile("^(\\(\\d{2}\\)|\\d{2})\\s?\\d{4}\\d?(-|\\s)?\\d{4}$");
        if (userDTO.getEmail().isBlank() || userDTO.getEmail() == null || userDTO.getNome().isBlank()
                || userDTO.getNome() == null || userDTO.getTelefone().isBlank() || userDTO.getTelefone() == null
                || userDTO.getSenha().isBlank() || userDTO.getSenha() == null
                || !p.matcher(userDTO.getEmail()).matches() || !pPassword.matcher(userDTO.getSenha()).matches()
                || !pPhone.matcher(userDTO.getTelefone()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        if (userService.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setSenha(bc.encode(user.getSenha()));
        user.setRole(roleService.findById(1).get());
        user = userService.save(user);
        try {
            request.login(userDTO.getEmail(), userDTO.getSenha());
        } catch (ServletException e) {
            Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.error("Erro ao authenticar usuário", e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user).generateDRO());
    }

    @GetMapping
    public ResponseEntity<Object> getAuthenticatedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Optional<User> user = userService.findByEmail(currentUserName);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(user.get().generateDRO());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestParam("email") String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get().generateDRO());

    }

    @GetMapping("/id")
    public ResponseEntity<Object> getNameById(@RequestParam("id") String id) {

        Optional<User> userOptional = userService.findById(UUID.fromString(id));
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get().getNome());

    }

    @PutMapping
    public ResponseEntity<Object> updateOne(@RequestBody UserDTO userDTO, @RequestParam("email") String email) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Pattern p = Pattern.compile("(.+)@(.+)");
        Pattern pPassword = Pattern
                .compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,32}$");
        Pattern pPhone = Pattern.compile("^(\\(\\d{2}\\)|\\d{2})\\s?\\d{4}\\d?(-|\\s)?\\d{4}$");
        if (userDTO.getEmail().isBlank() || userDTO.getEmail() == null || userDTO.getNome().isBlank()
                || userDTO.getNome() == null || userDTO.getTelefone().isBlank() || userDTO.getTelefone() == null
                || userDTO.getSenha().isBlank() || userDTO.getSenha() == null
                || !p.matcher(userDTO.getEmail()).matches() || !pPassword.matcher(userDTO.getSenha()).matches()
                || !pPhone.matcher(userDTO.getTelefone()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user).generateDRO());
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
