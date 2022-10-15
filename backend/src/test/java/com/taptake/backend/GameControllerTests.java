package com.taptake.backend;


import com.taptake.backend.DTO.GameDTO;
import com.taptake.backend.controller.GameController;
import com.taptake.backend.model.Game;
import com.taptake.backend.service.GameService;
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
@SpringBootTest(classes = GameController.class)
class GameControllerTests {

    @MockBean
    GameService gameService;

    @Autowired
    @InjectMocks
    GameController gameController;

    @Test
    void saveValidGame(){
        Mockito.when(gameService.findByNome(anyString())).thenReturn(Optional.of(new Game()));
        GameDTO gameDTO = new GameDTO();
        gameDTO.setNome("teste");
        gameDTO.setTipoJogo("FPS");
        gameDTO.setQuantidadeJogadores(5);
        ResponseEntity<?> re = gameController.save(gameDTO);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void saveInvalidGame(){
        var game = new Game();
        game.setNome("Test");
        game.setTipoJogo("FPS");
        game.setQuantidadeJogadores(5);
        Mockito.when(gameService.findByNome(anyString())).thenReturn(Optional.of(game));
        GameDTO gameDTO = new GameDTO();
        gameDTO.setNome("Test");
        gameDTO.setTipoJogo("FPS");
        gameDTO.setQuantidadeJogadores(5);
        ResponseEntity<?> re = gameController.save(gameDTO);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void recoverGameValid(){
        UUID validId = UUID.randomUUID();
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(new Game()));
        ResponseEntity<?> re = gameController.findById(validId.toString());
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void recoverGameInvalid(){
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = gameController.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND,re.getStatusCode());
    }

    @Test
    void updateValidPlayer(){
        Game game = new Game();
        game.setNome("");
        game.setQuantidadeJogadores(0);
        game.setTipoJogo("");
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));
        GameDTO gameDTO = new GameDTO();
        gameDTO.setNome("teste");
        ResponseEntity<?> re = gameController.update(gameDTO,UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void updateInvalidPlayer(){
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.empty());
        GameDTO gameDTO = new GameDTO();
        ResponseEntity<?> re = gameController.update(gameDTO,UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK,re.getStatusCode());
    }

    @Test
    void deletePlayer() {
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = gameController.deleteOne(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT,re.getStatusCode());
    }







}
