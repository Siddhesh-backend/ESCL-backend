package com.escl.repository;

import com.escl.model.MatchResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultsRepository extends JpaRepository<MatchResults, Long> {
}