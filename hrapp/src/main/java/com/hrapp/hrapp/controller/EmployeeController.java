package com.hrapp.hrapp.controller;

import com.hrapp.hrapp.model.Employee;
import com.hrapp.hrapp.model.Department;
import com.hrapp.hrapp.model.Position;
import com.hrapp.hrapp.repository.EmployeeRepository;
import com.hrapp.hrapp.repository.DepartmentRepository;
import com.hrapp.hrapp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    // 1. Tüm çalışanları getir
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. ID ile çalışan getir
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR') or hasRole('EMPLOYEE')")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // 3. Belirli departmandaki çalışanları getir
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping("/department/{deptId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long deptId) {
        Optional<Department> dept = departmentRepository.findById(deptId);
        return dept.map(employeeRepository::findByDepartment).orElse(null);
    }

    // 4. Belirli pozisyondaki çalışanları getir
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping("/position/{posId}")
    public List<Employee> getEmployeesByPosition(@PathVariable Long posId) {
        Optional<Position> pos = positionRepository.findById(posId);
        return pos.map(employeeRepository::findByPosition).orElse(null);
    }

    // 5. Yeni çalışan ekle
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // 6. Çalışan sil
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    // 7. Çalışanı güncelle
    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setBirthDate(updatedEmployee.getBirthDate());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhone(updatedEmployee.getPhone());
            employee.setAddress(updatedEmployee.getAddress());
            employee.setHireDate(updatedEmployee.getHireDate());
            employee.setEmergencyContact(updatedEmployee.getEmergencyContact());
            employee.setEducationHistory(updatedEmployee.getEducationHistory());
            employee.setCertifications(updatedEmployee.getCertifications());
            employee.setPerformanceRecords(updatedEmployee.getPerformanceRecords());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setManager(updatedEmployee.getManager());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

}
