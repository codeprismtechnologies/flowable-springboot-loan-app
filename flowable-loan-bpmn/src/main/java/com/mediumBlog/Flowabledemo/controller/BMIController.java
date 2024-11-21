package com.mediumBlog.Flowabledemo.controller;

import org.flowable.cmmn.engine.configurator.impl.process.DefaultProcessInstanceService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/bmi")
public class BMIController {
    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/calculate")
    public String calculateBMI(@RequestBody Map<String,Object> data) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("serviceTask", data);
        return "BMI calculated" +processInstance.getId();
    }
    @PostMapping("/result")
    public String getBMIResult(@RequestBody Map<String,Object> data) {
        System.out.println(data);
        return "";
    }
}
