package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.model.Employee;
import com.hrapp.hrapp.domain.model.LeaveRequest;
import com.hrapp.hrapp.domain.repository.EmployeeRepository;
import com.hrapp.hrapp.domain.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
    }

    public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId) {
        return leaveRequestRepository.findByEmployeeEmployeeId(employeeId);
    }

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        Long empId = leaveRequest.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus("Pending");

        LeaveRequest saved = leaveRequestRepository.save(leaveRequest);

        // Domain Event tetikle
        saved.markCreatedEvent();
        saved.getDomainEvents().forEach(eventPublisher::publishEvent);
        saved.clearDomainEvents();

        return saved;
    }

    public LeaveRequest updateLeaveStatus(Long leaveId, String status) {
        LeaveRequest leaveRequest = getLeaveRequestById(leaveId);
        leaveRequest.setStatus(status);

        LeaveRequest updated = leaveRequestRepository.save(leaveRequest);

        // Domain Event tetikle
        updated.markStatusUpdatedEvent(status);
        updated.getDomainEvents().forEach(eventPublisher::publishEvent);
        updated.clearDomainEvents();

        return updated;
    }


    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }

    public List<Object[]> getLeaveCountPerEmployee() {
        return leaveRequestRepository.getLeaveCountPerEmployee();
    }

    public List<Object[]> getLeaveRequestStats() {
        return leaveRequestRepository.getLeaveRequestStats();
    }
}
