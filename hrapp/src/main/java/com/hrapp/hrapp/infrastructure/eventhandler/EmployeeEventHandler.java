package com.hrapp.hrapp.infrastructure.eventhandler;

import com.hrapp.hrapp.domain.event.EmployeeCreatedEvent;
import com.hrapp.hrapp.domain.event.EmployeeDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventHandler {

    @EventListener
    public void handleCreated(EmployeeCreatedEvent event) {
        System.out.println("Yeni çalışan oluşturuldu: " + event.getEmail());
        // → log, mail veya audit işlemleri burada yapılabilir
    }

    @EventListener
    public void handleDeleted(EmployeeDeletedEvent event) {
        System.out.println("Çalışan silindi: ID = " + event.getEmployeeId());
        // → log, mail veya audit işlemleri burada yapılabilir
    }
}
