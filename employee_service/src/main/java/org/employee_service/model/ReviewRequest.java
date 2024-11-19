package org.employee_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ReviewRequest {

    @Id
    private int id;
    private String processId;
    private String userId;
    private String approvalState;

}
