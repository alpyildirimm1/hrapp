package com.hrapp.hrapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hrapp.hrapp.domain.event.PerformanceEvaluationCreatedEvent;
import com.hrapp.hrapp.domain.event.PerformanceEvaluationDeletedEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performance_evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate evaluationDate;
    private String evaluator;
    private String comments;
    private String rating;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"department", "position", "manager", "subordinates"})
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Transient
    private final List<Object> domainEvents = new ArrayList<>();

    public void markCreatedEvent() {
        domainEvents.add(new PerformanceEvaluationCreatedEvent(this.id, this.evaluator));
    }

    public void markDeletedEvent() {
        domainEvents.add(new PerformanceEvaluationDeletedEvent(this.id));
    }

    public List<Object> getDomainEvents() {
        return domainEvents;
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

}

