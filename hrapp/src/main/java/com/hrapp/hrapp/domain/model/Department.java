package com.hrapp.hrapp.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hrapp.hrapp.domain.event.DepartmentCreatedEvent;
import com.hrapp.hrapp.domain.event.DepartmentDeletedEvent;
import com.hrapp.hrapp.domain.model.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    private String name;
    private String description;
    private String goals;
    private String budget;
    private String performanceReports;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference(value = "department-employees")
    private List<Employee> employees;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;






    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getPerformanceReports() {
        return performanceReports;
    }

    public void setPerformanceReports(String performanceReports) {
        this.performanceReports = performanceReports;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }


    @Transient // JPA bunu görmesin diye
    private final List<Object> domainEvents = new ArrayList<>();

    public void markCreatedEvent() {
        domainEvents.add(new DepartmentCreatedEvent(this.departmentId, this.name));
    }

    public List<Object> getDomainEvents() {
        return domainEvents;
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
    public void markDeletedEvent() {
        domainEvents.add(new DepartmentDeletedEvent(this.departmentId));
    }

}
