package com.hrapp.hrapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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



/*
    public Department() {
    }

    // --- Tüm alanları içeren constructor ---
    public Department(Long departmentId, String name, String description, String goals,
                      String budget, String performanceReports,
                      List<Employee> employees, Employee manager) {
        this.departmentId = departmentId;
        this.name = name;
        this.description = description;
        this.goals = goals;
        this.budget = budget;
        this.performanceReports = performanceReports;
        this.employees = employees;
        this.manager = manager;
    }
*/


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
}
