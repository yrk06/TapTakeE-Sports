package com.taptake.backend.repository;

import com.taptake.backend.model.Match;
import com.taptake.backend.model.MatchParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface MatchParticipationRepository extends JpaRepository<MatchParticipation, UUID> {
    Optional<MatchParticipation> findByMatch(Match match);
}
