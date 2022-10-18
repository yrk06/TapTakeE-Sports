package com.taptake.backend;


import com.taptake.backend.DTO.MatchDTO;
import com.taptake.backend.controller.MatchController;
import com.taptake.backend.model.Championship;
import com.taptake.backend.model.Match;
import com.taptake.backend.service.ChampionshipService;
import com.taptake.backend.service.MatchService;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = MatchController.class)
class MatchControllerTests {

    @MockBean
    MatchService matchService;

    @MockBean
    ChampionshipService cs;

    @Autowired
    @InjectMocks
    MatchController mc;

    @Test
    void saveValidMatch(){
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(new Championship()));
        MatchDTO m = new MatchDTO();
        m.setIdCampeonato(UUID.randomUUID().toString());
        ResponseEntity<?> re = mc.save(m);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }
    @Test
    void saveInvalidMatch(){
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.empty());
        MatchDTO m = new MatchDTO();
        m.setIdCampeonato(UUID.randomUUID().toString());
        ResponseEntity<?> re = mc.save(m);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void findByValidId(){
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(new Match()));
        ResponseEntity<?> re = mc.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void findByInvalidId(){
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = mc.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
    @Test
    void findByValidChampionship(){
        Mockito.when(matchService.findByChampionship(any(Championship.class))).thenReturn(new LinkedList<>());
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(new Championship()));
        ResponseEntity<?> re = mc.findByChampionship(UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void findByInvalidChampionship(){
        Mockito.when(matchService.findByChampionship(any(Championship.class))).thenReturn(new LinkedList<>());
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = mc.findByChampionship(UUID.randomUUID().toString());
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void deleteOne(){
        ResponseEntity<?> re = mc.deleteOne(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }
    @Test
    void updateValidMatch(){
        Championship c1 = new Championship();

        UUID id = UUID.randomUUID();
        c1.setIdCampeonato(id);
        Match m = new Match();
        m.setChampionship(c1);
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(m));
        Championship c2 = new Championship();
        UUID id2 = UUID.randomUUID();
        c2.setIdCampeonato(id2);
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(c2));
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setIdCampeonato(id2.toString());
        ResponseEntity<?> re = mc.update(matchDTO,UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void updateMatchInvalidMatchId(){
        Championship c1 = new Championship();

        UUID id = UUID.randomUUID();
        c1.setIdCampeonato(id);
        Match m = new Match();
        m.setChampionship(c1);
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.empty());
        Championship c2 = new Championship();
        UUID id2 = UUID.randomUUID();
        c2.setIdCampeonato(id2);
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(c2));
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setIdCampeonato(id2.toString());
        ResponseEntity<?> re = mc.update(matchDTO,UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
    @Test
    void updateMatchInvalidOrg(){
        Championship c1 = new Championship();

        UUID id = UUID.randomUUID();
        c1.setIdCampeonato(id);
        Match m = new Match();
        m.setChampionship(c1);
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(m));
        Championship c2 = new Championship();
        UUID id2 = UUID.randomUUID();
        c2.setIdCampeonato(id2);
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.empty());
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setIdCampeonato(id2.toString());
        ResponseEntity<?> re = mc.update(matchDTO,UUID.randomUUID().toString());
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

}
