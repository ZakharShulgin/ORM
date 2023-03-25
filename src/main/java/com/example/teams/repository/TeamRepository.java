package com.example.teams.repository;

import com.example.teams.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository <Team, Long> {
}
