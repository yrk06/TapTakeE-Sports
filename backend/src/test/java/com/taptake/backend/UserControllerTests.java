package com.taptake.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.controller.UserController;
import com.taptake.backend.model.User;
import com.taptake.backend.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = UserController.class)
public class UserControllerTests {

    @MockBean
    UserService userService;

    @Autowired
    @InjectMocks
    private UserController controller;

    // Create
    @Test
    void savePersonValid() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        UserDTO testUser = new UserDTO();
        testUser.setEmail("test@test.test");
        testUser.setNome("Teste");
        testUser.setSenha("uuuuui");
        testUser.setTelefone("41 99999-9999");
        ResponseEntity<Object> re = controller.savePerson(testUser);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void savePersonAlreadyExists() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        UserDTO testUser = new UserDTO();
        testUser.setEmail("test@test.test");
        testUser.setNome("Teste");
        testUser.setSenha("uuuuui");
        testUser.setTelefone("41 99999-9999");
        ResponseEntity<Object> re = controller.savePerson(testUser);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    // Recovery

    @Test
    void recoverValidPerson() {
        Mockito.when(userService.findByEmail("teste@teste.com")).thenReturn(Optional.of(new User()));
        ResponseEntity<Object> re = controller.findByEmail("teste@teste.com");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void recoverInvalidPerson() {
        Mockito.when(userService.findByEmail("teste@teste.com")).thenReturn(Optional.empty());
        ResponseEntity<Object> re = controller.findByEmail("teste@teste.com");
        assertEquals(HttpStatus.NOT_FOUND,re.getStatusCode());
    }

    // Update

    @Test
    void updateValidPerson() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        UserDTO testUser = new UserDTO();
        testUser.setEmail("test@test.test");
        testUser.setNome("Teste");
        testUser.setSenha("uuuuui");
        testUser.setTelefone("41 99999-9999");
        ResponseEntity<Object> re = controller.updateOne(testUser,"teste@teste.com");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void updateInvalidPerson() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        UserDTO testUser = new UserDTO();
        testUser.setEmail("test@test.test");
        testUser.setNome("Teste");
        testUser.setSenha("uuuuui");
        testUser.setTelefone("41 99999-9999");
        ResponseEntity<Object> re = controller.updateOne(testUser,"teste@teste.com");
        assertEquals(HttpStatus.NOT_FOUND,re.getStatusCode());
    }

    // Delete

    @Test
    void deletePerson() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        ResponseEntity<Object> re = controller.deleteOne("teste@teste.com");
        assertEquals(HttpStatus.NO_CONTENT,re.getStatusCode());
    }
}
