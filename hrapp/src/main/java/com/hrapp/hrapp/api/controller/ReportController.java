package com.hrapp.hrapp.api.controller;

import com.hrapp.hrapp.application.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/average-performance")
    public Double getAveragePerformanceScore() {
        return reportService.getAveragePerformanceScore();
    }

    @GetMapping("/employee-count-by-department")
    public List<Object[]> getEmployeeCountByDepartment() {
        return reportService.getEmployeeCountByDepartment();
    }

    @GetMapping("/leave-status-stats")
    public List<Object[]> getLeaveStatusStats() {
        return reportService.getLeaveStatusStats();
    }

    @GetMapping("/leave-count-per-employee")
    public List<Object[]> getLeaveCountPerEmployee() {
        return reportService.getLeaveCountPerEmployee();
    }
}
