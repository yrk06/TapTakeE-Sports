package com.taptake.backend;


import com.taptake.backend.DTO.GameDTO;
import com.taptake.backend.DTO.OrganizationDTO;
import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.DTO.UserTeamDTO;
import com.taptake.backend.controller.UserTeamController;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.User;
import com.taptake.backend.model.UserTeam;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.UserService;
import com.taptake.backend.service.UserTeamService;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = UserTeamController.class)
public class UserTeamControllerTests {

    @MockBean
    UserTeamService userTeamService;

    @MockBean
    UserService userService;

    @MockBean
    GameService gameService;

    @Autowired
    @InjectMocks
    UserTeamController userTeamController;

    @Test
    void saveValidUserTeam() {
        UserTeamDTO userTeam = new UserTeamDTO();
        User user = new User();
        Game game = new Game();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        userTeam.setIdJogo(game.getIdJogo().toString());
        userTeam.setIdUsuario(user.getIdUsuario().toString());

        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeam);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void saveInvalidUserTeam() {
        UserTeamDTO userTeam = new UserTeamDTO();
        User user = new User();
        Game game = new Game();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        userTeam.setIdJogo(game.getIdJogo().toString());
        userTeam.setIdUsuario(user.getIdUsuario().toString());

        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(new UserTeam()));
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeam);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void saveInvalidUser() {
        UserTeamDTO userTeam = new UserTeamDTO();
        User user = new User();
        Game game = new Game();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        userTeam.setIdJogo(game.getIdJogo().toString());
        userTeam.setIdUsuario(user.getIdUsuario().toString());

        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeam);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

    @Test
    void saveInvalidGame() {
        UserTeamDTO userTeam = new UserTeamDTO();
        User user = new User();
        Game game = new Game();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        userTeam.setIdJogo(game.getIdJogo().toString());
        userTeam.setIdUsuario(user.getIdUsuario().toString());

        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResponseEntity<?> re = userTeamController.save(userTeam);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

    @Test
    void getValidUserteam() {
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(new UserTeam()));
        ResponseEntity<?> re = userTeamController.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void getInvalidUserteam() {
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = userTeamController.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void deleteUserTeam() {
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = userTeamController.delete(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }

}
