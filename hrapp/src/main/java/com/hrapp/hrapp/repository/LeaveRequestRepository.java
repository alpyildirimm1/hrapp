package com.hrapp.hrapp.repository;

import com.hrapp.hrapp.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeEmployeeId(Long employeeId);
    @Query("SELECT l.employee.firstName, l.employee.lastName, COUNT(l) " +
            "FROM LeaveRequest l GROUP BY l.employee.firstName, l.employee.lastName")
    List<Object[]> getLeaveCountPerEmployee();

    @Query("SELECT l.status, COUNT(l) FROM LeaveRequest l GROUP BY l.status")
    List<Object[]> getLeaveRequestStats();


}
