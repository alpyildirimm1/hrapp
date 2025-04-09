package com.hrapp.hrapp.controller;

import com.hrapp.hrapp.repository.DepartmentRepository;
import com.hrapp.hrapp.repository.LeaveRequestRepository;
import com.hrapp.hrapp.repository.PerformanceEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private PerformanceEvaluationRepository performanceRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private LeaveRequestRepository leaveRequestRepo;

    // Ortalama performans puanı
    @GetMapping("/average-performance")
    public Double getAveragePerformanceScore() {
        return performanceRepo.getAveragePerformanceScore();
    }

    // Departman bazlı çalışan sayısı
    @GetMapping("/employee-count-by-department")
    public List<Object[]> getEmployeeCountByDepartment() {
        return departmentRepo.getEmployeeCountByDepartment();
    }

    // İzin durumlarının dağılımı
    @GetMapping("/leave-status-stats")
    public List<Object[]> getLeaveStatusStats() {
        return leaveRequestRepo.getLeaveRequestStats();
    }

    // Her çalışanın kaç tane izni olduğu
    @GetMapping("/leave-count-per-employee")
    public List<Object[]> getLeaveCountPerEmployee() {
        return leaveRequestRepo.getLeaveCountPerEmployee();
    }
}
