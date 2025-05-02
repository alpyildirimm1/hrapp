package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.event.PerformanceEvaluationDeletedEvent;
import com.hrapp.hrapp.domain.model.Employee;
import com.hrapp.hrapp.domain.model.PerformanceEvaluation;
import com.hrapp.hrapp.domain.repository.EmployeeRepository;
import com.hrapp.hrapp.domain.repository.PerformanceEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceEvaluationService {

    @Autowired
    private PerformanceEvaluationRepository evaluationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public List<PerformanceEvaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public PerformanceEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    public List<PerformanceEvaluation> getEvaluationsByEmployeeId(Long employeeId) {
        return evaluationRepository.findByEmployeeEmployeeId(employeeId);
    }

    public PerformanceEvaluation createEvaluation(PerformanceEvaluation evaluation) {
        Long employeeId = evaluation.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        evaluation.setEmployee(employee);

        PerformanceEvaluation saved = evaluationRepository.save(evaluation);

        // Domain eventi işaretle ve yayınla
        saved.markCreatedEvent();
        saved.getDomainEvents().forEach(eventPublisher::publishEvent);
        saved.clearDomainEvents();

        return saved;
    }


    public PerformanceEvaluation updateEvaluation(Long id, PerformanceEvaluation updated) {
        PerformanceEvaluation existing = getEvaluationById(id);

        existing.setComments(updated.getComments());
        existing.setRating(updated.getRating());
        existing.setEvaluationDate(updated.getEvaluationDate());
        existing.setEvaluator(updated.getEvaluator());

        Employee employee = employeeRepository.findById(updated.getEmployee().getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setEmployee(employee);

        return evaluationRepository.save(existing);
    }

    public void deleteEvaluation(Long id) {
        evaluationRepository.findById(id).ifPresent(evaluation -> {
            evaluation.markDeletedEvent();
            eventPublisher.publishEvent(new PerformanceEvaluationDeletedEvent(evaluation.getId()));
            evaluation.clearDomainEvents();
            evaluationRepository.delete(evaluation);
        });
    }

}
