package com.hrapp.hrapp.infrastructure.eventhandler;

import com.hrapp.hrapp.domain.event.PositionCreatedEvent;
import com.hrapp.hrapp.domain.event.PositionDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PositionEventHandler {

    @EventListener
    public void handleCreated(PositionCreatedEvent event) {
        System.out.println("Yeni pozisyon oluşturuldu: " + event.getTitle());
    }

    @EventListener
    public void handleDeleted(PositionDeletedEvent event) {
        System.out.println("Pozisyon silindi: ID = " + event.getPositionId());
    }
}
