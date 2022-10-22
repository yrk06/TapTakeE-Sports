package com.taptake.backend.repository;

import com.taptake.backend.model.Match;
import com.taptake.backend.model.MatchPerformance;
import com.taptake.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MatchPerformanceRepository extends JpaRepository<MatchPerformance, UUID> {
    List<MatchPerformance> findByPlayer (Player p);

    List<MatchPerformance> findByMatch(Match m);
}
