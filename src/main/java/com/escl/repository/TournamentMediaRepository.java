package com.escl.repository;

import com.escl.model.TournamentMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentMediaRepository
        extends JpaRepository<TournamentMedia, Long> {
}