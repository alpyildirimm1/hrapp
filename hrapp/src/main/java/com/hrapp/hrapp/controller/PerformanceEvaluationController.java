package com.hrapp.hrapp.controller;

import com.hrapp.hrapp.model.Employee;
import com.hrapp.hrapp.model.PerformanceEvaluation;
import com.hrapp.hrapp.repository.DepartmentRepository;
import com.hrapp.hrapp.repository.EmployeeRepository;
import com.hrapp.hrapp.repository.PerformanceEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceEvaluationController {

    @Autowired
    private PerformanceEvaluationRepository repo;

    @Autowired
    private DepartmentRepository depRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    // Değerlendirme oluşturma (HR ve ADMIN)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PostMapping
    public PerformanceEvaluation create(@RequestBody PerformanceEvaluation evaluation) {
        return repo.save(evaluation);
    }

    // Tüm değerlendirmeleri getir (HR ve ADMIN)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping
    public List<PerformanceEvaluation> getAll() {
        return repo.findAll();
    }

    // Belirli çalışanın değerlendirmeleri (HR, ADMIN, EMPLOYEE kendi ID'si)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR') or hasRole('EMPLOYEE')")
    @GetMapping("/employee/{id}")
    public List<PerformanceEvaluation> getByEmployee(@PathVariable Long id) {
        return repo.findByEmployeeEmployeeId(id);
    }

    // Değerlendirme güncelleme (HR ve ADMIN)
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PutMapping("/{id}")
    public PerformanceEvaluation update(@PathVariable Long id, @RequestBody PerformanceEvaluation updated) {
        PerformanceEvaluation eval = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance Evaluation not found"));

        eval.setComments(updated.getComments());
        eval.setRating(updated.getRating());
        eval.setEvaluationDate(updated.getEvaluationDate());
        eval.setEvaluator(updated.getEvaluator());

        Employee employee = employeeRepo.findById(updated.getEmployee().getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        eval.setEmployee(employee);

        return repo.save(eval);
    }
}
