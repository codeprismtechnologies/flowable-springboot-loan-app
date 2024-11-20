package com.mediumBlog.Flowabledemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mediumBlog.Flowabledemo.entity.User;

import com.mediumBlog.Flowabledemo.serviceImpl.LoanRequest;
import com.mediumBlog.Flowabledemo.serviceImpl.ReviewRequest;
import com.mediumBlog.Flowabledemo.serviceImpl.UserService;

import org.flowable.dmn.api.DmnDecision;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mediumBlog.Flowabledemo.service.FlowableService;

@RestController
@RequestMapping("/api/")
public class Controller {
	@Autowired
	private FlowableService flowableService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private UserService userService;

	/*
	 * This api is for to initiate a complain request and assigend to checker
	 */
	@PostMapping("maker/loanRequest")
	public String makerRequest(@RequestBody LoanRequest loanRequest) {
		return flowableService.makeLoanRequest(loanRequest);
	}
	@PostMapping("maker/reviewLoanRequest")
	public String reviewLoanRequest(@RequestBody ReviewRequest request) {
		return flowableService.reviewLoanRequest(request);
	}
	@PostMapping("maker/test")
	public void test(@RequestBody  String test){
		System.out.println(test.toString());
	}

	@PostMapping("/deployment/check/{processId}")
	public String checkDeployment(@PathVariable("processId") String processId) {
		List<DmnDecision> exerciseAssignmentProcess = repositoryService.getDecisionsForProcessDefinition("loanApp");
		System.out.println(exerciseAssignmentProcess.size());
		return "";
	}

	/**
	 * Get active process instance details by process instance ID.
	 */
	@GetMapping("/process/active/{processInstanceId}")
	public ResponseEntity<?> getActiveProcessInstance(@PathVariable String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();

		if (processInstance == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Process instance not found or already completed.");
		}

		Map<String, Object> response = new HashMap<>();
		response.put("id", processInstance.getId());
		response.put("processDefinitionId", processInstance.getProcessDefinitionId());
		response.put("businessKey", processInstance.getBusinessKey());
		response.put("isSuspended", processInstance.isSuspended());
		response.put("variables", runtimeService.getVariables(processInstance.getId()));

		return ResponseEntity.ok(response);
	}

	/**
	 * Get historical process instance details by process instance ID.
	 */
	@GetMapping("/process/history/{processInstanceId}")
	public ResponseEntity<?> getHistoricalProcessInstance(@PathVariable String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();

		if (historicProcessInstance == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Process instance not found.");
		}

		Map<String, Object> response = new HashMap<>();
		response.put("id", historicProcessInstance.getId());
		response.put("processDefinitionId", historicProcessInstance.getProcessDefinitionId());
		response.put("startTime", historicProcessInstance.getStartTime());
		response.put("endTime", historicProcessInstance.getEndTime());
		response.put("durationInMillis", historicProcessInstance.getDurationInMillis());
		response.put("variables", historyService.createHistoricVariableInstanceQuery()
				.processInstanceId(historicProcessInstance.getId())
				.list());
		List<DmnDecision> exerciseAssignmentProcess = repositoryService.getDecisionsForProcessDefinition(historicProcessInstance.getProcessDefinitionId());
		System.out.println(exerciseAssignmentProcess.size());

		return ResponseEntity.ok(response);
	}

	/*
	 * This api is for get checker pending tasks which are assigned by maker
	 */
	@GetMapping("checker/pending/tasks")
	public Map<String, Object> checkerPending() {
		Map<String, Object> response = new HashMap<String, Object>();
		response = flowableService.getCheckerPendings();
		return response;
	}

	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		try {
			User savedUser = userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	/*
	 * This api is for review the task which are pending at checker 
	 */
	@PostMapping("/checker/review/task/{processId}/{approve}")
	public String checkerReviewReview(@PathVariable("processId") String processId,
			@PathVariable("approve") Boolean approve) {
		String respons = flowableService.checkerReview(processId, approve);
		return respons;
	}

	/*
	 * This api is for get maker return pending tasks which are assigned and rejected by checker level
	 */
	@GetMapping("maker/return/pending/tasks")
	public Map<String, Object> getMakerReturnPendings() {
		Map<String, Object> response = new HashMap<String, Object>();
		response = flowableService.getMakerReturnPendings();
		return response;
	}
	
	/*
	 * This api is for review the maker return pending tasks which are pending at maker level 
	 */ 
	@PostMapping("/makerReview/{complain}/{processId}/{approve}")
	public String makerReviewReturn(@PathVariable("complain") String complain,
			@PathVariable("processId") String processId, @PathVariable("approve") Boolean approve) {
		String respons = flowableService.makerReviewReturn(complain, processId, approve);
		return respons;
	}

}
