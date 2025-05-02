package com.hrapp.hrapp.domain.repository;

import com.hrapp.hrapp.domain.model.Department;
import com.hrapp.hrapp.domain.model.Employee;
import com.hrapp.hrapp.domain.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);

    List<Employee> findByPosition(Position position);
}
