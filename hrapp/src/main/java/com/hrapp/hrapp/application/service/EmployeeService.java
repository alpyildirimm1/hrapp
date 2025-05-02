package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.model.Department;
import com.hrapp.hrapp.domain.model.Employee;
import com.hrapp.hrapp.domain.model.Position;
import com.hrapp.hrapp.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        boolean isNew = (employee.getEmployeeId() == null); // ID yoksa yeni kayıt

        Employee saved = employeeRepository.save(employee); // Normal kaydet

        if (isNew) {
            saved.markCreatedEvent(); // Domain nesnesinde event ekle
            saved.getDomainEvents().forEach(eventPublisher::publishEvent); // Event'leri yay
            saved.clearDomainEvents(); // Listeyi temizle
        }

        return saved;
    }


    public void deleteById(Long id) {
        employeeRepository.findById(id).ifPresent(employee -> {
            employee.markDeletedEvent(); // Domain nesnesinde event ekle
            employee.getDomainEvents().forEach(eventPublisher::publishEvent); // Event'leri yay
            employee.clearDomainEvents(); // Listeyi temizle
            employeeRepository.deleteById(id); // Sonra sil
        });
    }


    public List<Employee> findByDepartmentId(Long departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> findByPositionId(Long positionId) {
        Position position = new Position();
        position.setPositionId(positionId);
        return employeeRepository.findByPosition(position);
    }

    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> findByPosition(Position position) {
        return employeeRepository.findByPosition(position);
    }


    public Employee updateEmployee(Long id, Employee updatedEmployee) {
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


