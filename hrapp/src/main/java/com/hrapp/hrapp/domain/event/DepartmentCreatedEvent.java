package com.hrapp.hrapp.domain.event;

public class DepartmentCreatedEvent {
    private final Long departmentId;
    private final String name;

    public DepartmentCreatedEvent(Long departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }
}
