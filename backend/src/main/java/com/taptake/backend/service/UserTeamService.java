package com.taptake.backend.service;

import com.taptake.backend.model.User;
import com.taptake.backend.model.UserTeam;
import com.taptake.backend.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserTeamService {

    @Autowired
    private UserTeamRepository userTeamRepository;

    @TransactionScoped
    public UserTeam save(UserTeam userTeam) {
        return userTeamRepository.save(userTeam);
    }

    public void delete(UUID id) {
        userTeamRepository.deleteById(id);
    }

    public List<UserTeam> findByUser(User user) {
        return userTeamRepository.findByUser(user);
    }

    public Optional<UserTeam> findById(UUID id) {
        return userTeamRepository.findById(id);
    }

    public UserTeam update(UserTeam userTeam) {
        return userTeamRepository.save(userTeam);
    }
}
