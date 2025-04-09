package com.hrapp.hrapp.repository;

import com.hrapp.hrapp.model.Department;
import com.hrapp.hrapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
    Department findByManager(Employee manager);
    @Query("SELECT d.name, COUNT(e) FROM Department d JOIN d.employees e GROUP BY d.name")
    List<Object[]> getEmployeeCountByDepartment();

}
