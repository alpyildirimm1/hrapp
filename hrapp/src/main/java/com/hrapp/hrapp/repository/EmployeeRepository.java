package com.hrapp.hrapp.repository;

import com.hrapp.hrapp.model.Department;
import com.hrapp.hrapp.model.Employee;
import com.hrapp.hrapp.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);

    List<Employee> findByPosition(Position position);
}
