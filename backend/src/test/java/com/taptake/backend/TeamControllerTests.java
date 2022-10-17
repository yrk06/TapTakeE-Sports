package com.taptake.backend;

import com.taptake.backend.DTO.TeamDTO;
import com.taptake.backend.controller.TeamController;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Organization;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.OrganizationService;
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

import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TeamController.class)
class TeamControllerTests {

    @MockBean
    GameService gs;

    @MockBean
    OrganizationService os;

    @MockBean
    TeamService ts;

    @Autowired
    @InjectMocks
    TeamController teamController;

    @Test
    void saveValidTeam(){
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(new Game()));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(new Organization()));
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdJogo(UUID.randomUUID().toString());
        teamDTO.setIdOrg(UUID.randomUUID().toString());
        teamDTO.setNomeTime("TESTE");
        ResponseEntity<?> re = teamController.save(teamDTO);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void saveTeamInvalidGameId(){
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(new Organization()));
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdJogo(UUID.randomUUID().toString());
        teamDTO.setIdOrg(UUID.randomUUID().toString());
        teamDTO.setNomeTime("TESTE");
        ResponseEntity<?> re = teamController.save(teamDTO);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }
    @Test
    void saveTeamInvalidOrgId(){
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(new Game()));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.empty());
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdJogo(UUID.randomUUID().toString());
        teamDTO.setIdOrg(UUID.randomUUID().toString());
        teamDTO.setNomeTime("TESTE");
        ResponseEntity<?> re = teamController.save(teamDTO);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }
    @Test
    void saveInvalidTeam(){
        LinkedList<Team> lt = new LinkedList<>();
        UUID idOrg = UUID.randomUUID();
        UUID idJogo = UUID.randomUUID();
        Game g = new Game();
        g.setIdJogo(idJogo);
        Organization org = new Organization();
        org.setIdOrg(idOrg);
        Team t = new Team();
        t.setOrg(org);
        t.setGame(g);
        t.setNomeTime("teste");
        lt.add(t);
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(g));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(org));
        Mockito.when(ts.findAllByNomeTime(anyString())).thenReturn(lt);
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdJogo(idJogo.toString());
        teamDTO.setIdOrg(idOrg.toString());
        teamDTO.setNomeTime("teste");

        ResponseEntity<?> re = teamController.save(teamDTO);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void deleteValid(){
        ResponseEntity<?> re = teamController.deleteOne(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }
    @Test
    void findByValidId(){
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(new Team()));
        ResponseEntity<?> re = teamController.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void findByInvalidId(){
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = teamController.findById(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
    @Test
    void findByNomeTime(){
        Mockito.when(ts.findAllByNomeTime(anyString())).thenReturn(new LinkedList<>());
        ResponseEntity<?> re = teamController.findByNomeTime("teste");
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void findByOrg(){
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(new Organization()));
        Mockito.when(ts.findAllByOrg(any(Organization.class))).thenReturn(new LinkedList<>());
        ResponseEntity<?> re = teamController.findByOrg(UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void findByInvalidOrg(){
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(ts.findAllByOrg(any(Organization.class))).thenReturn(new LinkedList<>());
        ResponseEntity<?> re = teamController.findByOrg(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void getAllByValidGame(){
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(new Game()));
        Mockito.when(ts.findAllByGame(any(Game.class))).thenReturn(new LinkedList<>());
        ResponseEntity<?> re = teamController.getAllByGame(UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void getAllByInvalidGame(){
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(ts.findAllByGame(any(Game.class))).thenReturn(new LinkedList<>());
        ResponseEntity<?> re = teamController.getAllByGame(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void updateValidTeam(){
        UUID idOrg = UUID.randomUUID();
        UUID idJogo = UUID.randomUUID();
        Game g = new Game();
        g.setIdJogo(idJogo);
        Organization org = new Organization();
        org.setIdOrg(idOrg);
        Team t = new Team();
        t.setOrg(org);
        t.setGame(g);
        t.setNomeTime("teste");
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(g));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(org));
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(t));
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdOrg(idOrg.toString());
        teamDTO.setIdJogo(idJogo.toString());
        teamDTO.setNomeTime("teste2");
        ResponseEntity<?> re = teamController.update(teamDTO, UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void updateValidTeamWithoutChanges(){
        UUID idOrg = UUID.randomUUID();
        UUID idJogo = UUID.randomUUID();
        Game g = new Game();
        g.setIdJogo(idJogo);
        Organization org = new Organization();
        org.setIdOrg(idOrg);
        Team t = new Team();
        t.setOrg(org);
        t.setGame(g);
        t.setNomeTime("teste");
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(g));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(org));
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(t));
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdOrg(idOrg.toString());
        teamDTO.setIdJogo(idJogo.toString());
        teamDTO.setNomeTime("teste");
        ResponseEntity<?> re = teamController.update(teamDTO, UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void updateTeamInvalidGame(){
        UUID idOrg = UUID.randomUUID();
        UUID idJogo = UUID.randomUUID();
        Game g = new Game();
        g.setIdJogo(idJogo);
        Organization org = new Organization();
        org.setIdOrg(idOrg);
        Team t = new Team();
        t.setOrg(org);
        t.setGame(g);
        t.setNomeTime("teste");
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(org));
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(t));
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdOrg(idOrg.toString());
        teamDTO.setIdJogo(idJogo.toString());
        ResponseEntity<?> re = teamController.update(teamDTO, UUID.randomUUID().toString());
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void updateTeamInvalidOrg(){
        UUID idOrg = UUID.randomUUID();
        UUID idJogo = UUID.randomUUID();
        Game g = new Game();
        g.setIdJogo(idJogo);
        Organization org = new Organization();
        org.setIdOrg(idOrg);
        Team t = new Team();
        t.setOrg(org);
        t.setGame(g);
        t.setNomeTime("teste");
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(g));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.of(t));
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdOrg(idOrg.toString());
        teamDTO.setIdJogo(idJogo.toString());
        ResponseEntity<?> re = teamController.update(teamDTO, UUID.randomUUID().toString());
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void updateTeamInvalidTeam(){
        UUID idOrg = UUID.randomUUID();
        UUID idJogo = UUID.randomUUID();
        Game g = new Game();
        g.setIdJogo(idJogo);
        Organization org = new Organization();
        org.setIdOrg(idOrg);
        Team t = new Team();
        t.setOrg(org);
        t.setGame(g);
        t.setNomeTime("teste");
        Mockito.when(gs.findById(any(UUID.class))).thenReturn(Optional.of(g));
        Mockito.when(os.findById(any(UUID.class))).thenReturn(Optional.of(org));
        Mockito.when(ts.findById(any(UUID.class))).thenReturn(Optional.empty());
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdOrg(idOrg.toString());
        teamDTO.setIdJogo(idJogo.toString());
        ResponseEntity<?> re = teamController.update(teamDTO, UUID.randomUUID().toString());
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }




}
