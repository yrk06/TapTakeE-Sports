package com.taptake.backend.repository;

import com.taptake.backend.model.Game;
import com.taptake.backend.model.Organization;
import com.taptake.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    List<Team> findAllByOrg(Organization org);

    List<Team> findAllByNomeTime(String nomeTime);

    List<Team> findAllByGame(Game game);
}
