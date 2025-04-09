package com.hrapp.hrapp.controller;

import com.hrapp.hrapp.model.Employee;
import com.hrapp.hrapp.model.LeaveRequest;
import com.hrapp.hrapp.repository.EmployeeRepository;
import com.hrapp.hrapp.repository.LeaveRequestRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestController(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN') or hasRole('HR')")
    @PostMapping("/create")
    public LeaveRequest createLeave(@RequestBody LeaveRequest leaveRequest) {
        Long empId = leaveRequest.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Çalışan bulunamadı"));
        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus("Pending");
        return leaveRequestRepository.save(leaveRequest);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN') or hasRole('HR')")
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveRequestRepository.findByEmployeeEmployeeId(employeeId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PutMapping("/{leaveId}/status")
    public LeaveRequest updateLeaveStatus(@PathVariable Long leaveId, @RequestParam String status) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("İzin isteği bulunamadı."));
        leaveRequest.setStatus(status);
        return leaveRequestRepository.save(leaveRequest);
    }
}
