package com.escl.repository;

import com.escl.model.TournamentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentDetailsRepository
        extends JpaRepository<TournamentDetails, Long> {
}