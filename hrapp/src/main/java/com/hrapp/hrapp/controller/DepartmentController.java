package com.hrapp.hrapp.controller;

import com.hrapp.hrapp.model.Department;
import com.hrapp.hrapp.model.Employee;
import com.hrapp.hrapp.repository.DepartmentRepository;
import com.hrapp.hrapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/name/{name}")
    public Department getDepartmentByName(@PathVariable String name) {
        return departmentRepository.findByName(name);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @GetMapping("/manager/{managerId}")
    public Department getDepartmentByManager(@PathVariable Long managerId) {
        Employee manager = employeeRepository.findById(managerId).orElse(null);
        return manager != null ? departmentRepository.findByManager(manager) : null;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDept) {
        return departmentRepository.findById(id).map(department -> {
            department.setName(updatedDept.getName());
            department.setDescription(updatedDept.getDescription());
            department.setGoals(updatedDept.getGoals());
            department.setBudget(updatedDept.getBudget());
            department.setPerformanceReports(updatedDept.getPerformanceReports());
            department.setManager(updatedDept.getManager());
            return departmentRepository.save(department);
        }).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}
