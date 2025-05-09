package com.hrapp.hrapp.domain.repository;

import com.hrapp.hrapp.domain.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByTitle(String title);
    List<Position> findByExperienceLevel(String experienceLevel);
}
