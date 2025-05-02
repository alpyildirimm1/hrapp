package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.event.DepartmentCreatedEvent;
import com.hrapp.hrapp.domain.event.DepartmentDeletedEvent;
import com.hrapp.hrapp.domain.model.Department;
import com.hrapp.hrapp.domain.model.Employee;
import com.hrapp.hrapp.domain.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    public Department findByManager(Employee manager) {
        return departmentRepository.findByManager(manager);
    }

    public Department saveDepartment(Department department) {
        boolean isNew = (department.getDepartmentId() == null);

        Department saved = departmentRepository.save(department);

        if (isNew) {
            saved.markCreatedEvent(); // domain nesnesi event'i ekliyor
            saved.getDomainEvents().forEach(eventPublisher::publishEvent);
            saved.clearDomainEvents();
        }

        return saved;
    }

    public void deleteById(Long id) {
        departmentRepository.findById(id).ifPresent(department -> {
            // 1. Event tetikle
            department.markDeletedEvent();
            eventPublisher.publishEvent(new DepartmentDeletedEvent(department.getDepartmentId()));
            department.clearDomainEvents();

            // 2. Bu departmana bağlı tüm çalışanları temizle
            if (department.getEmployees() != null) {
                department.getEmployees().forEach(emp -> emp.setDepartment(null));
            }

            // 3. Sil
            departmentRepository.delete(department);
        });
    }


    public Department updateDepartment(Department existingDepartment, Department updatedDepartment) {
        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(updatedDepartment.getDescription());
        existingDepartment.setGoals(updatedDepartment.getGoals());
        existingDepartment.setBudget(updatedDepartment.getBudget());
        existingDepartment.setPerformanceReports(updatedDepartment.getPerformanceReports());
        existingDepartment.setManager(updatedDepartment.getManager());
        return departmentRepository.save(existingDepartment);
    }

    public List<Object[]> getEmployeeCountByDepartment() {
        return departmentRepository.getEmployeeCountByDepartment();
    }
}
