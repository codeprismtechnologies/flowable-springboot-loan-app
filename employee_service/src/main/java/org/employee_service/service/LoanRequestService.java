package org.employee_service.service;

import org.employee_service.model.Employee;
import org.employee_service.model.LoanRequestDTO;
import org.employee_service.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanRequestService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;

    public String initiateLoanRequest(int userId) {

        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + userId));

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setId( employee.getId());
        loanRequestDTO.setCountry(employee.getCountry());
        loanRequestDTO.setAge(employee.getAge());
        loanRequestDTO.setAmount(employee.getAmount());

        String externalApiUrl = "http://localhost:8082/api/maker/loanRequest";
        try {
            String response = restTemplate.postForObject(externalApiUrl, loanRequestDTO, String.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while calling external API", e);
        }

    }
}
