package com.hrapp.hrapp.domain.event;

public class LeaveRequestCreatedEvent {
    private final Long leaveId;
    private final Long employeeId;

    public LeaveRequestCreatedEvent(Long leaveId, Long employeeId) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
