package com.taptake.backend.repository;

import com.taptake.backend.model.PlayerUserTeam;
import com.taptake.backend.model.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerUserTeamRepository extends JpaRepository<PlayerUserTeam, UUID> {

    List<PlayerUserTeam> findByUserTeam(UserTeam userteam);
}
