package com.mediumBlog.Flowabledemo.serviceImpl;

import lombok.Data;

@Data
public class ReviewRequest {
    private String processId;
    private String approved;
}
