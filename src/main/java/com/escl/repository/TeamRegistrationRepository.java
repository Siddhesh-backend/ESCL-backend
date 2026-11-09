
package com.escl.repository;

import com.escl.model.TeamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRegistrationRepository
        extends JpaRepository<TeamRegistration, Long> {

    List<TeamRegistration> findAllByTeamStatusIgnoreCaseOrderByCreatedAtDesc(
            String teamStatus
    );
}