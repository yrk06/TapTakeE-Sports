package com.taptake.backend;


import com.taptake.backend.DTO.MatchDTO;
import com.taptake.backend.controller.MatchController;
import com.taptake.backend.model.*;
import com.taptake.backend.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = MatchController.class)
class MatchControllerTests {

    @MockBean
    MatchService matchService;

    @MockBean
    ChampionshipService cs;

    @MockBean
    TeamService ts;

    @MockBean
    MatchPerformanceService mps;

    @MockBean
    PlayerService ps;

    @Autowired
    @InjectMocks
    MatchController mc;

    @Test
    void saveValidMatch(){
        UUID ID = UUID.randomUUID();
        Championship c = new Championship();
        c.setIdCampeonato(ID);
        Date d = new Date();
        Match m = new Match();
        Set<Team> st = new HashSet<>();
        m.setChampionship(c);
        m.setData(d);
        m.setEquipes(st);
        m.setIdPartida(UUID.randomUUID());
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(new Team()));
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(new Championship()));
        Mockito.when(matchService.save(any(Match.class))).thenReturn(m);
        MatchDTO md = new MatchDTO();
        md.setIdEquipes(new LinkedList<>());
        md.setData(d);
        md.setIdCampeonato(ID.toString());
        ResponseEntity<?> re = mc.save(md);
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
    void saveMatchInvalidTeamList(){
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(new Championship()));
        MatchDTO m = new MatchDTO();
        ArrayList<String> list = new ArrayList<String>();
        list.add(UUID.randomUUID().toString());
        m.setIdEquipes(list);
        m.setData(new Date());
        m.setIdCampeonato(UUID.randomUUID().toString());
        ResponseEntity<?> re = mc.save(m);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void findByValidId(){
        Match m = new Match();
        m.setEquipes(new HashSet<>());
        m.setChampionship(new Championship());
        m.setData(new Date());
        m.setIdPartida(UUID.randomUUID());
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(m));
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
        Date d = new Date();
        Team t = new Team();
        UUID idTeam = UUID.randomUUID();
        t.setIdEquipe(idTeam);
        Set<Team> st= new HashSet<>();
        st.add(t);

        UUID id = UUID.randomUUID();
        c1.setIdCampeonato(id);
        Match m = new Match();
        m.setChampionship(c1);
        m.setData(d);
        m.setEquipes(st);
        m.setIdPartida(UUID.randomUUID());
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(m));
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(t));
        Championship c2 = new Championship();
        UUID id2 = UUID.randomUUID();
        c2.setIdCampeonato(id2);
        Mockito.when(cs.findById(any(UUID.class))).thenReturn(Optional.of(c2));
        Mockito.when(matchService.update(any(Match.class))).thenReturn(m);
        MatchDTO matchDTO = new MatchDTO();
        Date d2 = new Date();
        matchDTO.setIdCampeonato(id2.toString());
        matchDTO.setData(d2);
        List<String> ids= new ArrayList<>();
        ids.add(idTeam.toString());
        matchDTO.setIdEquipes(new ArrayList<>());

        ResponseEntity<?> re = mc.update(matchDTO,UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void updateMatchInvalidMatchId(){
        Championship c1 = new Championship();

        UUID id = UUID.randomUUID();
        c1.setIdCampeonato(id);
        Match m = new Match();
        m.setIdPartida(UUID.randomUUID());
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
    @Test
    void updateScoreValid(){
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(new Match()));
        Mockito.when(ps.findById(any(UUID.class))).thenReturn(Optional.of(new Player()));
        UUID id = UUID.randomUUID();
        Player p = new Player();
        p.setIdJogador(id);
        ArrayList<MatchPerformance> lst = new ArrayList<>();
        MatchPerformance mp = new MatchPerformance();
        mp.setPlayer(p);
        lst.add(mp);
        Mockito.when(mps.findByMatch(any(Match.class))).thenReturn(lst);
        ResponseEntity<?> re = mc.recordScore(UUID.randomUUID().toString(), id.toString(), 12);
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void updateScoreInvalidMatch(){
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = mc.recordScore(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 12);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void updateScoreInvalidPlayer(){
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(new Match()));
        Mockito.when(ps.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = mc.recordScore(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 12);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

    @Test
    void updateScorePlayerNotFound(){
        Mockito.when(matchService.findById(any(UUID.class))).thenReturn(Optional.of(new Match()));
        Mockito.when(ps.findById(any(UUID.class))).thenReturn(Optional.of(new Player()));
        Mockito.when(mps.findByMatch(any(Match.class))).thenReturn(new ArrayList<>());
        ResponseEntity<?> re = mc.recordScore(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 12);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

}
