package com.taptake.backend.repository;

import com.taptake.backend.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, UUID> {

    public List<Championship> findAllByNome(String nome);

}
