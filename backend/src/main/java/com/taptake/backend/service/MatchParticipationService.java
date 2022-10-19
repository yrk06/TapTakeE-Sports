package com.taptake.backend.service;

import com.taptake.backend.model.Match;
import com.taptake.backend.model.MatchParticipation;
import com.taptake.backend.repository.MatchParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchParticipationService {

    @Autowired
    private MatchParticipationRepository mpr;

    @Transactional
    private MatchParticipation save(MatchParticipation mp){
        return mpr.save(mp);
    }
    private Optional<MatchParticipation> findById(UUID id){
        return mpr.findById(id);
    }
    private Optional<MatchParticipation> findByMatch(Match match){
        return mpr.findByMatch(match);
    }
    private void deleteOne(UUID id){
        mpr.deleteById(id);
    }
    private MatchParticipation update(MatchParticipation mp){
        return mpr.save(mp);
    }
}
