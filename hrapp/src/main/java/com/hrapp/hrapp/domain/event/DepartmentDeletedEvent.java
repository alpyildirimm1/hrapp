package com.hrapp.hrapp.domain.event;

public class DepartmentDeletedEvent {
    private final Long departmentId;

    public DepartmentDeletedEvent(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}
