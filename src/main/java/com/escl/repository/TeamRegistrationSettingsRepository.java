
package com.escl.repository;

import com.escl.model.TeamRegistrationSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRegistrationSettingsRepository
        extends JpaRepository<TeamRegistrationSettings, Long> {
}

