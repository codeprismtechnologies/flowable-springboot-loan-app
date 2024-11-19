package org.employee_service.repo;

import org.employee_service.model.ReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRequestRepository extends JpaRepository<ReviewRequest, Integer> {

    long deleteByProcessId(String processId);
}