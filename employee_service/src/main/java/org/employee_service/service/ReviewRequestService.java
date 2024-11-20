package org.employee_service.service;

import org.employee_service.model.ReviewRequest;
import org.employee_service.repo.ReviewRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewRequestService {

    @Autowired
    private ReviewRequestRepository reviewRequestRepository;

    public ReviewRequest saveReviewRequest(ReviewRequest reviewRequest) {
        return reviewRequestRepository.save(reviewRequest);
    }

    public void deleteReviewRequest(String processInstanceId) {
        reviewRequestRepository.deleteByProcessId(processInstanceId);
    }

    public List<ReviewRequest> getAllReviewRequests() {
        return reviewRequestRepository.findAll();
    }
}
