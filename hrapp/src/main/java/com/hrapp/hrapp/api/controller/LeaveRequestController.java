package com.hrapp.hrapp.api.controller;

import com.hrapp.hrapp.application.service.LeaveRequestService;
import com.hrapp.hrapp.domain.model.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN') or hasRole('HR')")
    @PostMapping("/create")
    public LeaveRequest createLeave(@RequestBody LeaveRequest leaveRequest) {
        return leaveRequestService.createLeaveRequest(leaveRequest);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestService.getAllLeaveRequests();
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN') or hasRole('HR')")
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveRequestService.getLeaveRequestsByEmployeeId(employeeId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('HR')")
    @PutMapping("/{leaveId}/status")
    public LeaveRequest updateLeaveStatus(@PathVariable Long leaveId, @RequestParam String status) {
        return leaveRequestService.updateLeaveStatus(leaveId, status);
    }
}
