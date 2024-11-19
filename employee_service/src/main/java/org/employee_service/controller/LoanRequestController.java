package org.employee_service.controller;

import org.employee_service.model.LoanApproval;
import org.employee_service.model.ReviewRequest;
import org.employee_service.service.EmployeeService;
import org.employee_service.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanRequestController {
    @Autowired
    private ReviewRequestController reviewRequestController;
    @Autowired
    private EmployeeService employeeService;

    /*
    initiate loan request
    POST http://localhost:8082/api/maker/loanRequest
Content-Type: application/json

{
  "id": 1,
  "country": "INDIA",
  "age": 22,
  "amount": 111111110
}

     */
    @PostMapping("/reviewRequest")
    public void reviewRequest(@RequestBody ReviewRequest loanApproval) {
        reviewRequestController.saveReviewRequest(loanApproval);
    }
    @PostMapping("/reviewRequest")
    public void saveApprovalRequest(@RequestBody LoanApproval loanApproval) {
        employeeService.updateUserLoanStatus(loanApproval);
    }


}
