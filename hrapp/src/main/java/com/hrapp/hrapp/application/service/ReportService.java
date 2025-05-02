package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.repository.DepartmentRepository;
import com.hrapp.hrapp.domain.repository.LeaveRequestRepository;
import com.hrapp.hrapp.domain.repository.PerformanceEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private PerformanceEvaluationRepository performanceRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private LeaveRequestRepository leaveRequestRepo;

    public Double getAveragePerformanceScore() {
        return performanceRepo.getAveragePerformanceScore();
    }

    public List<Object[]> getEmployeeCountByDepartment() {
        return departmentRepo.getEmployeeCountByDepartment();
    }

    public List<Object[]> getLeaveStatusStats() {
        return leaveRequestRepo.getLeaveRequestStats();
    }

    public List<Object[]> getLeaveCountPerEmployee() {
        return leaveRequestRepo.getLeaveCountPerEmployee();
    }
}
