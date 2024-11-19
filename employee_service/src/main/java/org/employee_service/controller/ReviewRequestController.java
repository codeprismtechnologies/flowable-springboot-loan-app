package org.employee_service.controller;

import org.employee_service.model.ReviewRequest;
import org.employee_service.service.ReviewRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviewrequest")
public class ReviewRequestController {
    
    @Autowired
    private ReviewRequestService reviewRequestService;
    
    @PostMapping("/save")
    public ResponseEntity<ReviewRequest> saveReviewRequest(@RequestBody ReviewRequest reviewRequest) {
        ReviewRequest savedRequest = reviewRequestService.saveReviewRequest(reviewRequest);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

}