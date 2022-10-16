package com.taptake.backend;



import com.taptake.backend.DTO.ChampionshipDTO;
import com.taptake.backend.controller.ChampionshipController;
import com.taptake.backend.model.Championship;
import com.taptake.backend.model.Game;
import com.taptake.backend.service.ChampionshipService;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ChampionshipController.class)
public class ChampionshipControllerTests {

    @MockBean
    ChampionshipService cs;



    @Autowired
    @InjectMocks
    ChampionshipController cc;

    @MockBean
    GameService gs;

    @Test
    void saveValidChampionship(){
        Mockito.when(cs.findAllByNome(anyString())).thenReturn(new ArrayList<>());
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(new Game()));
        ChampionshipDTO c = new ChampionshipDTO();
        c.setNome("teste");
        c.setPremiacao(12);
        c.setLocalCampeonato("teste");
        c.setIdJogo(UUID.randomUUID().toString());
        ResponseEntity<?> re = cc.save(c);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void saveInvalidChampionship(){
        LinkedList<Championship> lc = new LinkedList<>();
        Game g = new Game();
        UUID id = UUID.randomUUID();
        g.setIdJogo(id);
        Championship championship = new Championship();
        championship.setGame(g);
        championship.setNome("teste");
        championship.setPremiacao(12);
        championship.setLocalCampeonato("teste");
        lc.add(championship);
        Mockito.when(cs.findAllByNome(anyString())).thenReturn(lc);
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(g));
        ChampionshipDTO c = new ChampionshipDTO();
        c.setNome("teste");
        c.setPremiacao(12);
        c.setLocalCampeonato("teste");
        c.setIdJogo(id.toString());
        ResponseEntity<?> re = cc.save(c);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void recoverValidChampionshipById(){
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(new Championship()));
        UUID validId = UUID.randomUUID();
        ResponseEntity<?> re = cc.findById(validId.toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void recoverInvalidChampionshipById(){
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.empty());
        UUID validId = UUID.randomUUID();
        ResponseEntity<?> re = cc.findById(validId.toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void recoverValidChampionsListByNome(){
        ResponseEntity<?> re = cc.findAllByNome("teste");
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }



    @Test
    void updateValidChampionship(){
        UUID validId = UUID.randomUUID();
        Championship c = new Championship();
        Game g= new Game();
        g.setIdJogo(validId);
        c.setGame(g);
        c.setNome("teste");
        c.setLocalCampeonato("teste");
        c.setPremiacao(12);
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(c));
        Mockito.when(gs.findById(validId)).thenReturn(Optional.of(g));
        ChampionshipDTO cd = new ChampionshipDTO();
        cd.setNome("teste");
        cd.setPremiacao(12);
        cd.setLocalCampeonato("test");
        cd.setIdJogo(validId.toString());
        ResponseEntity<?> re = cc.update(cd, UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void updateChampionshipInvalidG(){
        UUID validId = UUID.randomUUID();
        Championship c = new Championship();
        Game g= new Game();
        g.setIdJogo(validId);
        c.setGame(g);
        c.setNome("teste");
        c.setLocalCampeonato("teste");
        c.setPremiacao(12);
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(c));
        Mockito.when(gs.findById(validId)).thenReturn(Optional.empty());
        ChampionshipDTO cd = new ChampionshipDTO();
        cd.setNome("teste");
        cd.setPremiacao(12);
        cd.setLocalCampeonato("teste");
        cd.setIdJogo(validId.toString());
        ResponseEntity<?> re = cc.update(cd, UUID.randomUUID().toString());
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void updateChampionshipInvalidC(){
        UUID validId = UUID.randomUUID();
        Game g = new Game();
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(gs.findById(validId)).thenReturn(Optional.of(g));
        ChampionshipDTO cd = new ChampionshipDTO();
        cd.setNome("teste");
        cd.setPremiacao(12);
        cd.setLocalCampeonato("teste");
        cd.setIdJogo(validId.toString());
        ResponseEntity<?> re = cc.update(cd, UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void DeleteValidChampionship(){
        ResponseEntity<?> re = cc.delete(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }



}
