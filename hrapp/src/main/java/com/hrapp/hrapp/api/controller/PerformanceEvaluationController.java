package com.hrapp.hrapp.api.controller;

import com.hrapp.hrapp.application.service.PerformanceEvaluationService;
import com.hrapp.hrapp.domain.model.PerformanceEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceEvaluationController {

    @Autowired
    private PerformanceEvaluationService evaluationService;

    // Değerlendirme oluşturma (HR ve ADMIN)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PostMapping
    public PerformanceEvaluation create(@RequestBody PerformanceEvaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    // Tüm değerlendirmeleri getir (HR ve ADMIN)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping
    public List<PerformanceEvaluation> getAll() {
        return evaluationService.getAllEvaluations();
    }

    // Belirli çalışanın değerlendirmeleri (HR, ADMIN, EMPLOYEE kendi ID'si)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR') or hasRole('EMPLOYEE')")
    @GetMapping("/employee/{id}")
    public List<PerformanceEvaluation> getByEmployee(@PathVariable Long id) {
        return evaluationService.getEvaluationsByEmployeeId(id);
    }

    // Değerlendirme güncelleme (HR ve ADMIN)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PutMapping("/{id}")
    public PerformanceEvaluation update(@PathVariable Long id, @RequestBody PerformanceEvaluation updated) {
        return evaluationService.updateEvaluation(id, updated);
    }
}
