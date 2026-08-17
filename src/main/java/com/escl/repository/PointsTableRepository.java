package com.escl.repository;


import com.escl.model.PointsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsTableRepository extends JpaRepository<PointsTable, Long> {
    boolean existsBySeasonAndTeamName(String season, String teamName);
}
