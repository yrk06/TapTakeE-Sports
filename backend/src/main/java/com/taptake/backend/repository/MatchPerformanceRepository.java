package com.taptake.backend.repository;

import com.taptake.backend.model.Match;
import com.taptake.backend.model.MatchPerformance;
import com.taptake.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchPerformanceRepository extends JpaRepository<MatchPerformance, Integer> {

    List<MatchPerformance> findByPlayer(Player p);

    List<MatchPerformance> findByMatch(Match m);

    @Query("SELECT mp FROM MatchPerformance mp JOIN mp.match m WHERE m.data >= ?1 AND m.data < ?2 AND mp.player = ?3")
    List<MatchPerformance> findByPeriodAndPlayer(Date lowerBound, Date upperBound, Player player);
}
