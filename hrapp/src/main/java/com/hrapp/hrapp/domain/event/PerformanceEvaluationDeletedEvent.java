package com.hrapp.hrapp.domain.event;

public class PerformanceEvaluationDeletedEvent {
    private final Long evaluationId;

    public PerformanceEvaluationDeletedEvent(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }
}
