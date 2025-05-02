package com.hrapp.hrapp.domain.repository;

import com.hrapp.hrapp.domain.model.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {
    List<PerformanceEvaluation> findByEmployeeEmployeeId(Long employeeId);
    @Query("SELECT AVG(CASE " +
            "WHEN p.rating = 'A+' THEN 5 " +
            "WHEN p.rating = 'A' THEN 4 " +
            "WHEN p.rating = 'B+' THEN 3 " +
            "WHEN p.rating = 'B' THEN 2 " +
            "WHEN p.rating = 'C' THEN 1 " +
            "ELSE 0 END) " +
            "FROM PerformanceEvaluation p")
    Double getAveragePerformanceScore();

}

