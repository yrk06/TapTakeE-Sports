package com.taptake.backend;


import com.taptake.backend.DTO.PlayerDTO;
import com.taptake.backend.DTO.UserDTO;
import com.taptake.backend.controller.PlayerController;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.User;
import com.taptake.backend.service.PlayerService;
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
import org.springframework.security.core.parameters.P;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = PlayerController.class)
public class PlayerControllerTests {

    @MockBean
    PlayerService playerService;

    @Autowired
    @InjectMocks
    private PlayerController playerController;


    @Test
    void savePlayerValid(){
        Mockito.when(playerService.findByNome(anyString())).thenReturn(Optional.empty());
        PlayerDTO playerDTOtest = new PlayerDTO();
        playerDTOtest.setNome("Test");
        playerDTOtest.setCargo("Test");
        playerDTOtest.setIdEquipe(UUID.fromString("1232464613"));
        ResponseEntity<?> re = playerController.save(playerDTOtest);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void savePlayerAlreadyExists(){
        var player = new Player();
        player.setNome("Test");
        player.setCargo("Test");
        player.setIdEquipe(UUID.fromString("1232464613"));
        Mockito.when(playerService.findByNome(anyString())).thenReturn(Optional.of(player));
        PlayerDTO playerDTOtest = new PlayerDTO();
        playerDTOtest.setNome("Test");
        playerDTOtest.setCargo("Test");
        playerDTOtest.setIdEquipe(UUID.fromString("1232464613"));
        ResponseEntity<?> re = playerController.save(playerDTOtest);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void recoverPlayerValid(){
        Mockito.when(playerService.findById(UUID.fromString("teste"))).thenReturn(Optional.of(new Player()));
        ResponseEntity<?> re = playerController.findById("teste");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }
    @Test
    void recoverInvalidPlayer(){
        Mockito.when(playerService.findById(UUID.fromString("teste"))).thenReturn(Optional.empty());
        ResponseEntity<?> re = playerController.findById("teste");
        assertEquals(HttpStatus.NOT_FOUND,re.getStatusCode());
    }

    @Test
    void updateValidPlayer(){
        Mockito.when(playerService.findById(UUID.fromString(anyString()))).thenReturn(Optional.of(new Player()));
        PlayerDTO testUser = new PlayerDTO();
        testUser.setCargo("test");
        testUser.setNome("Test");
        testUser.setIdEquipe(UUID.fromString("uuuuui"));
        ResponseEntity<?> re = playerController.update(testUser,"uuuuui");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }
    @Test
    void updateInvalidPlayer(){
        Mockito.when(playerService.findById(UUID.fromString(anyString()))).thenReturn(Optional.empty() );
        PlayerDTO testUser = new PlayerDTO();
        testUser.setCargo("test");
        testUser.setNome("Test");
        testUser.setIdEquipe(UUID.fromString("uuuuui"));
        ResponseEntity<?> re = playerController.update(testUser,"uuuuui");
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }
    @Test
    void deletePerson() {
        Mockito.when(playerService.findById(UUID.fromString(anyString()))).thenReturn(Optional.empty());
        ResponseEntity<?> re = playerController.deleteOne("teste@teste.com");
        assertEquals(HttpStatus.NO_CONTENT,re.getStatusCode());
    }
}
