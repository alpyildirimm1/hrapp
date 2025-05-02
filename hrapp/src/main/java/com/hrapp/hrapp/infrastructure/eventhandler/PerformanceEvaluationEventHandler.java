package com.hrapp.hrapp.infrastructure.eventhandler;

import com.hrapp.hrapp.domain.event.PerformanceEvaluationCreatedEvent;
import com.hrapp.hrapp.domain.event.PerformanceEvaluationDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PerformanceEvaluationEventHandler {

    @EventListener
    public void handleCreated(PerformanceEvaluationCreatedEvent event) {
        System.out.println("Değerlendirme oluşturuldu: ID=" + event.getEvaluationId() + ", Evaluator=" + event.getEvaluator());
    }

    @EventListener
    public void handleDeleted(PerformanceEvaluationDeletedEvent event) {
        System.out.println("Değerlendirme silindi: ID=" + event.getEvaluationId());
    }
}

