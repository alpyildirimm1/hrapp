package com.hrapp.hrapp.api.controller;

import com.hrapp.hrapp.application.service.DepartmentService;
import com.hrapp.hrapp.application.service.EmployeeService;
import com.hrapp.hrapp.domain.model.Department;
import com.hrapp.hrapp.domain.model.Employee;
import com.hrapp.hrapp.domain.repository.DepartmentRepository;
import com.hrapp.hrapp.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.findById(id).orElse(null);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/name/{name}")
    public Department getDepartmentByName(@PathVariable String name) {
        return departmentService.findByName(name);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @GetMapping("/manager/{managerId}")
    public Department getDepartmentByManager(@PathVariable Long managerId) {
        Employee manager = employeeService.findById(managerId).orElse(null);
        return manager != null ? departmentService.findByManager(manager) : null;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDept) {
        return departmentService.findById(id).map(department -> {
            department.setName(updatedDept.getName());
            department.setDescription(updatedDept.getDescription());
            department.setGoals(updatedDept.getGoals());
            department.setBudget(updatedDept.getBudget());
            department.setPerformanceReports(updatedDept.getPerformanceReports());
            department.setManager(updatedDept.getManager());
            return departmentService.saveDepartment(department);
        }).orElse(null);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
    }
}
