package com.taptake.backend;

import com.taptake.backend.DRO.UserTeamDRO;
import com.taptake.backend.DTO.UserTeamDTO;
import com.taptake.backend.controller.UserTeamController;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.PlayerUserTeam;
import com.taptake.backend.model.Team;
import com.taptake.backend.model.User;
import com.taptake.backend.model.UserTeam;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.MatchPerformanceService;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.PlayerUserTeamService;
import com.taptake.backend.service.UserService;
import com.taptake.backend.service.UserTeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @MockBean
    PlayerService playerService;

    @MockBean
    PlayerUserTeamService playerUserTeamService;

    @MockBean
    MatchPerformanceService matchPerformanceService;

    @Autowired
    @InjectMocks
    UserTeamController userTeamController;

    @Test
    void saveValidUserTeam() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock user and game
        User user = new User();
        Game game = new Game();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        UserTeamDTO userTeamDTO = new UserTeamDTO();
        userTeamDTO.setIdJogo(game.getIdJogo().toString());
        userTeamDTO.setPlayers(new LinkedList<>());

        Mockito.when(userTeamService.findByUser(any(User.class))).thenReturn(new LinkedList<>());
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeamDTO);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void saveInvalidUserTeamExists() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        User user = new User();
        Game game = new Game();
        UserTeam userTeam = new UserTeam();
        userTeam.setGame(game);

        UserTeamDTO userTeamDTO = new UserTeamDTO();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        userTeamDTO.setIdJogo(game.getIdJogo().toString());

        Mockito.when(userTeamService.findByUser(any(User.class))).thenReturn(new LinkedList<>() {
            {
                add(userTeam);
            }
        });
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeamDTO);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void saveInvalidUser() {
        // Mock authenticated user
        Authentication authentication = Mockito.mock(AnonymousAuthenticationToken.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock user and game
        User user = new User();
        Game game = new Game();

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        UserTeamDTO userTeamDTO = new UserTeamDTO();
        userTeamDTO.setIdJogo(game.getIdJogo().toString());

        Mockito.when(userTeamService.findByUser(any(User.class))).thenReturn(new LinkedList<>());
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(any(UUID.class))).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeamDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, re.getStatusCode());
    }

    @Test
    void saveInvalidGame() {
        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock user and game
        User user = new User();
        Game game = new Game();
        UserTeam userTeam = new UserTeam();
        userTeam.setGame(game);

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        UserTeamDTO userTeamDTO = new UserTeamDTO();
        userTeamDTO.setIdJogo(UUID.randomUUID().toString());

        Mockito.when(userTeamService.findByUser(any(User.class))).thenReturn(new LinkedList<>());
        Mockito.when(userService.findById(any(UUID.class))).thenReturn(Optional.of(user));
        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(game.getIdJogo())).thenReturn(Optional.of(game));

        ResponseEntity<?> re = userTeamController.save(userTeamDTO);
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

    @Test
    void getValidUserteam() {
        UserTeam userTeam = Mockito.mock(UserTeam.class);
        Mockito.when(userTeam.generateUserTeamDRO()).thenReturn(new UserTeamDRO());
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));
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
    void deleteUserTeamValid() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock user and game
        User user = new User();
        Game game = new Game();
        UserTeam userTeam = new UserTeam();
        userTeam.setUser(user);
        userTeam.setIdEquipeUsuario(UUID.randomUUID());
        userTeam.setPlayers(new HashSet<>());

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));

        ResponseEntity<?> re = userTeamController.delete(userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }

    @Test
    void deleteUserTeamInvalid() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(AnonymousAuthenticationToken.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock user and game
        User user = new User();
        Game game = new Game();
        UserTeam userTeam = new UserTeam();
        userTeam.setUser(user);
        userTeam.setIdEquipeUsuario(UUID.randomUUID());

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));

        ResponseEntity<?> re = userTeamController.delete(userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.UNAUTHORIZED, re.getStatusCode());
    }

    @Test
    void deleteUserTeamUnauthorized() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock user and game
        User user = new User();
        Game game = new Game();
        UserTeam userTeam = new UserTeam();
        userTeam.setUser(new User());
        userTeam.setIdEquipeUsuario(UUID.randomUUID());

        user.setIdUsuario(UUID.randomUUID());
        game.setIdJogo(UUID.randomUUID());

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));

        ResponseEntity<?> re = userTeamController.delete(userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.UNAUTHORIZED, re.getStatusCode());
    }

    @Test
    void addActivePlayer() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserTeamDTO userTeamDTO = new UserTeamDTO();

        User user = new User();
        user.setIdUsuario(UUID.randomUUID());
        Game game = new Game();
        UserTeam userTeam = new UserTeam();

        game.setIdJogo(UUID.randomUUID());
        game.setQuantidadeJogadores(1);

        userTeam.setUser(user);
        userTeam.setIdEquipeUsuario(UUID.randomUUID());
        userTeam.setGame(game);
        userTeam.setPlayers(new HashSet<PlayerUserTeam>());

        userTeamDTO.setIdJogo(game.getIdJogo().toString());

        Player player = new Player();
        player.setIdJogador(UUID.randomUUID());

        userTeamDTO.setPlayers(new LinkedList<>() {
            {
                add(player.getIdJogador().toString());
            }
        });

        Team team = new Team();
        team.setGame(game);
        team.setIdEquipe(UUID.randomUUID());
        player.setTeam(team);

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));
        Mockito.when(userTeamService.update(any(UserTeam.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        Mockito.when(playerService.findById(player.getIdJogador())).thenReturn(Optional.of(player));
        Mockito.when(playerUserTeamService.save(any(PlayerUserTeam.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        ResponseEntity<?> re = userTeamController.update(userTeamDTO, userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());

        assertEquals(player, userTeam.getActivePlayers().get(0));
        assertEquals(1, userTeam.getPlayers().size());

    }

    @Test
    void addActivePlayerAlreadyExists() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserTeamDTO userTeamDTO = new UserTeamDTO();

        User user = new User();
        user.setIdUsuario(UUID.randomUUID());
        Game game = new Game();
        UserTeam userTeam = new UserTeam();

        game.setIdJogo(UUID.randomUUID());
        game.setQuantidadeJogadores(1);

        Player player = new Player();
        player.setIdJogador(UUID.randomUUID());

        userTeamDTO.setPlayers(new LinkedList<>() {
            {
                add(player.getIdJogador().toString());
            }
        });

        Team team = new Team();
        team.setGame(game);
        team.setIdEquipe(UUID.randomUUID());
        player.setTeam(team);

        userTeam.setUser(user);
        userTeam.setIdEquipeUsuario(UUID.randomUUID());
        userTeam.setGame(game);
        PlayerUserTeam pteam = new PlayerUserTeam();
        pteam.setPlayer(player);
        pteam.setDataEntrada(new Date());
        userTeam.setPlayers(new HashSet<PlayerUserTeam>() {
            {
                add(pteam);
            }
        });

        userTeamDTO.setIdJogo(game.getIdJogo().toString());

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));
        Mockito.when(userTeamService.update(any(UserTeam.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        Mockito.when(playerService.findById(player.getIdJogador())).thenReturn(Optional.of(player));

        ResponseEntity<?> re = userTeamController.update(userTeamDTO, userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());

        assertEquals(player, userTeam.getActivePlayers().get(0));
        assertEquals(1, userTeam.getPlayers().size());

    }

    @Test
    void removeActivePlayer() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserTeamDTO userTeamDTO = new UserTeamDTO();

        Player player = new Player();
        player.setIdJogador(UUID.randomUUID());

        userTeamDTO.setPlayers(new LinkedList<>());

        User user = new User();
        user.setIdUsuario(UUID.randomUUID());
        Game game = new Game();
        UserTeam userTeam = new UserTeam();

        game.setIdJogo(UUID.randomUUID());
        game.setQuantidadeJogadores(1);

        userTeam.setUser(user);
        userTeam.setIdEquipeUsuario(UUID.randomUUID());
        userTeam.setGame(game);
        PlayerUserTeam pteam = new PlayerUserTeam();
        pteam.setPlayer(player);
        pteam.setDataEntrada(new Date());
        userTeam.setPlayers(new HashSet<PlayerUserTeam>() {
            {
                add(pteam);
            }
        });

        userTeamDTO.setIdJogo(game.getIdJogo().toString());

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));
        Mockito.when(userTeamService.update(any(UserTeam.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        Mockito.when(playerService.findById(player.getIdJogador())).thenReturn(Optional.of(player));

        ResponseEntity<?> re = userTeamController.update(userTeamDTO, userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());

        assertEquals(0, userTeam.getActivePlayers().size());
        assertEquals(1, userTeam.getPlayers().size());

    }

    @Test
    void switchActivePlayer() {

        // Mock authenticated user
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("teste@teste");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserTeamDTO userTeamDTO = new UserTeamDTO();

        Player player = new Player();
        player.setIdJogador(UUID.randomUUID());

        Player player2 = new Player();
        player2.setIdJogador(UUID.randomUUID());

        userTeamDTO.setPlayers(new LinkedList<>() {
            {
                add(player2.getIdJogador().toString());
            }
        });

        User user = new User();
        user.setIdUsuario(UUID.randomUUID());
        Game game = new Game();
        UserTeam userTeam = new UserTeam();

        game.setIdJogo(UUID.randomUUID());
        game.setQuantidadeJogadores(1);

        Team team = new Team();
        team.setGame(game);
        team.setIdEquipe(UUID.randomUUID());
        player.setTeam(team);
        player2.setTeam(team);

        userTeam.setUser(user);
        userTeam.setIdEquipeUsuario(UUID.randomUUID());
        userTeam.setGame(game);
        PlayerUserTeam pteam = new PlayerUserTeam();
        pteam.setPlayer(player);
        pteam.setDataEntrada(new Date());
        userTeam.setPlayers(new HashSet<PlayerUserTeam>() {
            {
                add(pteam);
            }
        });

        userTeamDTO.setIdJogo(game.getIdJogo().toString());

        Mockito.when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));
        Mockito.when(userTeamService.findById(any(UUID.class))).thenReturn(Optional.of(userTeam));
        Mockito.when(userTeamService.update(any(UserTeam.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        Mockito.when(playerService.findById(player.getIdJogador())).thenReturn(Optional.of(player));
        Mockito.when(playerService.findById(player2.getIdJogador())).thenReturn(Optional.of(player2));
        Mockito.when(playerUserTeamService.save(any(PlayerUserTeam.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        ResponseEntity<?> re = userTeamController.update(userTeamDTO, userTeam.getIdEquipeUsuario().toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());

        assertEquals(player2, userTeam.getActivePlayers().get(0));
        assertEquals(2, userTeam.getPlayers().size());

        for (PlayerUserTeam playert : userTeam.getPlayers()) {
            if (playert.getPlayer() == player) {
                assertNotEquals(null, playert.getDataSaida());
            }
        }

    }

}
