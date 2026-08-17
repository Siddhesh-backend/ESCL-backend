package com.escl.repository;

import com.escl.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    boolean existsBySeriesNameAndMatchNameAndMatchDate(
            String seriesName,
            String matchName,
            String matchDate
    );
}