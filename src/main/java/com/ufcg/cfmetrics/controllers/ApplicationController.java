package com.ufcg.cfmetrics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.cfmetrics.services.ApplicationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationService appService;
	
	@GetMapping
	@Operation(description = "Get All Applications")
	public ResponseEntity<?> getAllApplications() {
        return new ResponseEntity<>(appService.getAllApplications(), HttpStatus.OK);
    }
	
	@GetMapping("/process/{appId}")
	@Operation(description = "Get Application Process Information")
    public ResponseEntity<?> getApplicationProcessInfo(@PathVariable("appId") String applicationId) {
        return new ResponseEntity<>(appService.getApplicationProcessInfo(applicationId), HttpStatus.OK);
    }
	
	@GetMapping("/{appId}")
	@Operation(description = "Get An Application By Id")
    public ResponseEntity<?> getApplication(@PathVariable("appId") String applicationId) {
        return new ResponseEntity<>(appService.getApplication(applicationId), HttpStatus.OK);
    }
	
	@GetMapping("/env/{appId}")
	@Operation(description = "Get Environments Variavbles Application")
    public ResponseEntity<?> getApplicationEnvironmentVariabels(@PathVariable("appId") String applicationId) {
        return new ResponseEntity<>(appService.getApplicationEnvironmentVariabels(applicationId), HttpStatus.OK);
    }
	
	@GetMapping("/start/{appId}")
	@Operation(description = "Start An Application")
    public ResponseEntity<?> startApplication(@PathVariable("appId") String applicationId) {
        return new ResponseEntity<>(appService.startApplication(applicationId), HttpStatus.OK);
    }
	
	@GetMapping("/stop/{appId}")
	@Operation(description = "Stop An Application")
    public ResponseEntity<?> stopApplication(@PathVariable("appId") String applicationId) {
        return new ResponseEntity<>(appService.stopApplication(applicationId), HttpStatus.OK);
    }
}
