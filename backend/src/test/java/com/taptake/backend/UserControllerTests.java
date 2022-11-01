package com.taptake.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.mock.web.MockHttpServletRequest;

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
        User u = new User();
        u.setEmail("teste@teste.com");
        u.setSenha("aA5#");
        u.setTelefone("41 99999-9999");
        u.setNome("aaaaaaa");
        u.setIdUsuario(UUID.randomUUID());
        Mockito.when(userService.save(any(User.class))).thenReturn(u);
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        UserDTO testUser = new UserDTO();
        testUser.setEmail("teste@teste.com");
        testUser.setSenha("aA5#afghfh");
        testUser.setTelefone("41 99999-9999");
        testUser.setNome("aaaaaaa");
        HttpServletRequest mhttpsr = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<Object> re = controller.savePerson(mhttpsr, testUser);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void SavePersonInvalidRegex(){
        UserDTO testUser = new UserDTO();
        testUser.setEmail("teste@teste.com");
        testUser.setSenha("aA5#");
        testUser.setTelefone("asd341ge45");
        testUser.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        HttpServletRequest mhttpsr = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<Object> re = controller.savePerson(mhttpsr, testUser);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void savePersonAlreadyExists() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        UserDTO testUser = new UserDTO();
        testUser.setEmail("teste@teste.com");
        testUser.setSenha("aA5#aethb");
        testUser.setTelefone("41 99999 9999");
        testUser.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        MockHttpServletRequest mhttpsr = new MockHttpServletRequest();
        ResponseEntity<Object> re = controller.savePerson(mhttpsr, testUser);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    // Recovery

    @Test
    void recoverValidPerson() {
        User u = new User();
        u.setEmail("teste@teste.com");
        u.setSenha("aA5-");
        u.setTelefone("41 99999-9999");
        u.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        u.setIdUsuario(UUID.randomUUID());
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(u));
        ResponseEntity<Object> re = controller.findByEmail("teste@teste.com");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void recoverInvalidPerson() {
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        ResponseEntity<Object> re = controller.findByEmail("teste@teste.com");
        assertEquals(HttpStatus.NOT_FOUND,re.getStatusCode());
    }

    // Update

    @Test
    void updateValidPerson() {
        User u = new User();
        u.setEmail("teste@teste.com");
        u.setSenha("aA5#");
        u.setTelefone("41999999999");
        u.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        u.setIdUsuario(UUID.randomUUID());
        Mockito.when(userService.update(any(User.class))).thenReturn(u);
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        UserDTO testUser = new UserDTO();
        testUser.setEmail("teste@testee.com");
        testUser.setSenha("aA5#aertnçjo");
        testUser.setTelefone("41 99999 9999");
        testUser.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ResponseEntity<Object> re = controller.updateOne(testUser,"teste@teste.com");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void updateInvalidPerson() {
        User u = new User();
        u.setEmail("teste@teste.com");
        u.setSenha("aA5#");
        u.setTelefone("41 999999999");
        u.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        u.setIdUsuario(UUID.randomUUID());
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        Mockito.when(userService.update(any(User.class))).thenReturn(u);
        UserDTO testUser = new UserDTO();
        testUser.setEmail("test@test.test");
        testUser.setNome("Teste");
        testUser.setSenha("uuuuui");
        testUser.setTelefone("41 99999 9999");

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
