package com.mediumBlog.Flowabledemo.service;

import com.mediumBlog.Flowabledemo.serviceImpl.LoanRequest;
import com.mediumBlog.Flowabledemo.serviceImpl.ReviewRequest;

import java.util.Map;

public interface FlowableService {

	public Map<String,Object> getCheckerPendings();
	public String checkerReview(String processId,Boolean review);
	public Map<String,Object> getMakerReturnPendings();
	public String makerReviewReturn(String complaincomplain,String processId, Boolean review);
	String reviewLoanRequest(ReviewRequest request);
	String makeLoanRequest(LoanRequest request);
	}
