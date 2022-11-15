package com.taptake.backend;

import com.taptake.backend.DTO.PlayerDTO;
import com.taptake.backend.controller.PlayerController;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.TeamService;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = PlayerController.class)
public class PlayerControllerTests {

    @MockBean
    PlayerService playerService;

    @MockBean
    GameService gameService;

    @MockBean
    TeamService ts;

    @Autowired
    @InjectMocks
    private PlayerController playerController;

    @Test
    void savePlayerValid() {
        Mockito.when(playerService.findByNome(anyString())).thenReturn(Optional.empty());
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(new Team()));
        PlayerDTO playerDTOtest = new PlayerDTO();
        playerDTOtest.setIdEquipe(UUID.randomUUID().toString());
        playerDTOtest.setNome("Test");
        playerDTOtest.setCargo("Test");
        ResponseEntity<?> re = playerController.save(playerDTOtest);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void savePlayerAlreadyExists() {
        var player = new Player();
        UUID id = UUID.randomUUID();
        Team t = new Team();
        t.setIdEquipe(id);
        player.setTeam(t);
        player.setNome("Test");
        player.setCargo("Test");
        Mockito.when(playerService.findByNome(anyString())).thenReturn(Optional.of(player));
        PlayerDTO playerDTOtest = new PlayerDTO();
        playerDTOtest.setNome("Test");
        playerDTOtest.setCargo("Test");
        playerDTOtest.setIdEquipe(id.toString());
        ResponseEntity<?> re = playerController.save(playerDTOtest);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void recoverPlayerValid() {
        UUID validPlayer = UUID.randomUUID();
        Mockito.when(playerService.findById(validPlayer)).thenReturn(Optional.of(new Player()));
        ResponseEntity<?> re = playerController.findById(validPlayer.toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void recoverInvalidPlayer() {
        Mockito.when(playerService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = playerController.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void updateValidPlayer() {
        UUID validPlayer = UUID.randomUUID();
        UUID validT = UUID.randomUUID();
        Team t = new Team();
        t.setIdEquipe(validT);
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(t));
        Player player = new Player();
        player.setTeam(t);
        player.setNome("teste");
        player.setCargo("teste");
        Mockito.when(playerService.findById(any(UUID.class))).thenReturn(Optional.of(player));
        PlayerDTO testUser = new PlayerDTO();
        testUser.setCargo("test");
        testUser.setNome("Test");
        testUser.setIdEquipe(validT.toString());
        ResponseEntity<?> re = playerController.update(testUser, validPlayer.toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void updateInvalidPlayer() {
        Mockito.when(playerService.findById(any(UUID.class))).thenReturn(Optional.empty());
        PlayerDTO testUser = new PlayerDTO();
        testUser.setCargo("test");
        testUser.setNome("Test");
        ResponseEntity<?> re = playerController.update(testUser, UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void deletePlayer() {
        Player p = new Player();
        p.setIdJogador(UUID.randomUUID());
        Mockito.when(playerService.findById(any(UUID.class))).thenReturn(Optional.of(p));
        ResponseEntity<?> re = playerController.deleteOne(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }
}
