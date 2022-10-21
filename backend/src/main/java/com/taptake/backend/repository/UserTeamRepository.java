package com.taptake.backend.repository;

import com.taptake.backend.model.User;
import com.taptake.backend.model.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserTeamRepository extends JpaRepository<UserTeam, UUID> {

    Optional<UserTeam> findById(UUID id);
    List<UserTeam> findByUser(User user);
}
