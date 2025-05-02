package com.hrapp.hrapp.domain.event;

public class PerformanceEvaluationCreatedEvent {
    private final Long evaluationId;
    private final String evaluator;

    public PerformanceEvaluationCreatedEvent(Long evaluationId, String evaluator) {
        this.evaluationId = evaluationId;
        this.evaluator = evaluator;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public String getEvaluator() {
        return evaluator;
    }
}
