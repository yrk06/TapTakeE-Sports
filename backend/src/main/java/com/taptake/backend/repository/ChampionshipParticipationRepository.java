package com.taptake.backend.repository;

import com.taptake.backend.model.Championship;
import com.taptake.backend.model.ChampionshipParticipation;
import com.taptake.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChampionshipParticipationRepository extends JpaRepository<ChampionshipParticipation, UUID> {

    List<ChampionshipParticipation> findByChampionship(Championship championship);

    List<ChampionshipParticipation> findByTeam(Team team);
}
