package com.ufcg.cfmetrics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.cfmetrics.dto.ProcessStatsDTO;
import com.ufcg.cfmetrics.dto.ScaleProcessDTO;
import com.ufcg.cfmetrics.dto.TerminateProcessDTO;
import com.ufcg.cfmetrics.services.ProcessService;



@RestController
@RequestMapping("/process")
public class ProcessController {
	
	@Autowired
	private ProcessService processService;
	
	@DeleteMapping("/terminate/{processId}")
    public ResponseEntity<?> terminateProcess(@PathVariable("processId") String processId, @RequestBody TerminateProcessDTO terminateProcessDTO) {
		this.processService.terminateProcess(processId, terminateProcessDTO);
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/scale/{processId}")
    public ResponseEntity<?> scaleProcess(@PathVariable("processId") String processId, @RequestBody ScaleProcessDTO scaleProcessDTO) {
        return new ResponseEntity<>(processService.scaleProcess(processId, scaleProcessDTO), HttpStatus.OK);
	}
	
	@GetMapping("/{processId}")
    public ResponseEntity<?> getProcessById(@PathVariable("processId") String processId) {
        return new ResponseEntity<>(processService.getProcessById(processId), HttpStatus.OK);
	}
	
	@GetMapping("/app/{appId}")
    public ResponseEntity<?> getAllProcessByApp(@PathVariable("appId") String applicationId) {
        return new ResponseEntity<>(processService.getAllProcesses(applicationId), HttpStatus.OK);
	}
	
	@PostMapping("/stats/{appId}")
    public ResponseEntity<?> getProcessesByApp(@PathVariable("appId") String applicationId, 
    		@RequestBody ProcessStatsDTO processStatsDTO) {
        return new ResponseEntity<>(processService.getProcessesStatisticsByApp(applicationId, processStatsDTO), HttpStatus.OK);
	}
	
	@PostMapping("stats/agregated/{appId}")
	public ResponseEntity<?> getAllProcessesStatisticByApp(@PathVariable("appId") String applicationId, 
			@RequestBody ProcessStatsDTO processStatsDTO) {
        return new ResponseEntity<>(processService.getAllProcessesStatistic(applicationId, processStatsDTO), HttpStatus.OK);
	}
}
