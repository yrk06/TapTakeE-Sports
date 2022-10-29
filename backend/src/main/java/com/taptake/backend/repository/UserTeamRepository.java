package com.taptake.backend.repository;

import com.taptake.backend.model.User;
import com.taptake.backend.model.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, UUID> {

    Optional<UserTeam> findById(UUID id);

    List<UserTeam> findByUser(User user);
}
