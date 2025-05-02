package com.hrapp.hrapp.infrastructure.eventhandler;

import com.hrapp.hrapp.domain.event.DepartmentCreatedEvent;
import com.hrapp.hrapp.domain.event.DepartmentDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DepartmentEventHandler {

    @EventListener
    public void handleDepartmentCreated(DepartmentCreatedEvent event) {
        System.out.println("Yeni departman oluşturuldu: " + event.getName());
        // → Log tutma, notification, audit tablosuna yazma gibi işlemler yapılabilir
    }


    @EventListener
    public void handleDepartmentDeleted(DepartmentDeletedEvent event) {
        System.out.println("Departman silindi: ID = " + event.getDepartmentId());
        // → Buraya log/audit/email gibi başka işlemler de eklenebilir
    }

}

