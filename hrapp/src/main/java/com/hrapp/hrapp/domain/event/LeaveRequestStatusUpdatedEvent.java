package com.hrapp.hrapp.domain.event;

public class LeaveRequestStatusUpdatedEvent {
    private final Long leaveId;
    private final String newStatus;

    public LeaveRequestStatusUpdatedEvent(Long leaveId, String newStatus) {
        this.leaveId = leaveId;
        this.newStatus = newStatus;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
