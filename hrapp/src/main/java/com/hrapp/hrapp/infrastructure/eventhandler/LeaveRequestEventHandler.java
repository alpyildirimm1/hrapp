package com.hrapp.hrapp.infrastructure.eventhandler;

import com.hrapp.hrapp.domain.event.LeaveRequestCreatedEvent;
import com.hrapp.hrapp.domain.event.LeaveRequestStatusUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestEventHandler {

    @EventListener
    public void handleCreated(LeaveRequestCreatedEvent event) {
        System.out.println("Yeni izin talebi oluşturuldu. ID = " + event.getLeaveId()
                + ", Employee ID = " + event.getEmployeeId());
    }

    @EventListener
    public void handleStatusUpdated(LeaveRequestStatusUpdatedEvent event) {
        System.out.println("İzin talebi durumu güncellendi. ID = " + event.getLeaveId()
                + ", Yeni Durum = " + event.getNewStatus());
    }
}

