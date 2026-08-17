package com.escl.repository;

import com.escl.model.UpcomingSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpcomingSeriesRepository
        extends JpaRepository<UpcomingSeries, Long> {

    boolean existsBySeriesNameAndSeriesDate(
            String seriesName,
            String seriesDate
    );
}
