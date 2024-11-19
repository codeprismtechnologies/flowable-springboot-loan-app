package org.employee_service.service;

import org.employee_service.model.Employee;
import org.employee_service.model.LoanApproval;
import org.employee_service.model.ReviewRequest;
import org.employee_service.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ReviewRequestService reviewRequestService;

    public Employee saveUser(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getUser(int userId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(userId);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + userId);
        }
        return optionalEmployee.get();
    }

    public void updateUserLoanStatus(LoanApproval loanApproval) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(loanApproval.getUserId());
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + loanApproval.getUserId());
        }
        Employee employee = optionalEmployee.get();
        employee.setLoanStatus(loanApproval.getLoanStatus());
        employee.setProcessInstanceId(loanApproval.getProcessInstanceId());
        employeeRepository.save(employee);
        reviewRequestService.deleteReviewRequest(loanApproval.getProcessInstanceId());
    }
}
