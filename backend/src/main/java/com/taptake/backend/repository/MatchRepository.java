package com.taptake.backend.repository;

import com.taptake.backend.model.Championship;
import com.taptake.backend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
    List<Match> findByChampionship(Championship championship);
}
