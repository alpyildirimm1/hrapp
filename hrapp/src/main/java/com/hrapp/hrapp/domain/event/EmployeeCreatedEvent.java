package com.hrapp.hrapp.domain.event;

public class EmployeeCreatedEvent {
    private final Long employeeId;
    private final String email;

    public EmployeeCreatedEvent(Long employeeId, String email) {
        this.employeeId = employeeId;
        this.email = email;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmail() {
        return email;
    }
}
