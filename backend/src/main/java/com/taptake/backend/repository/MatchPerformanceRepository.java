package com.taptake.backend.repository;

import com.taptake.backend.model.MatchPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MatchPerformanceRepository extends JpaRepository<MatchPerformance, UUID> {
}
