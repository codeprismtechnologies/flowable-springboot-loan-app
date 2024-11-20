package org.employee_service.controller;
import org.employee_service.model.LoanApproval;
import org.employee_service.model.ReviewRequest;
import org.employee_service.service.EmployeeService;
import org.employee_service.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanRequestService;
    @Autowired
    private ReviewRequestController reviewRequestController;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/loanRequest/{userId}")
    public ResponseEntity<String> initiateLoanRequest(@PathVariable int userId) {
        String response = loanRequestService.initiateLoanRequest(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reviewRequest")
    public void reviewRequest(@RequestBody ReviewRequest reviewRequest) {
        reviewRequestController.saveReviewRequest(reviewRequest);
    }
    @PostMapping("/loanApproval")
    public void saveApprovalRequest(@RequestBody LoanApproval loanApproval) {
        employeeService.updateUserLoanStatus(loanApproval);
    }


}
