package com.hrapp.hrapp.domain.event;

public class EmployeeDeletedEvent {
    private final Long employeeId;

    public EmployeeDeletedEvent(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}