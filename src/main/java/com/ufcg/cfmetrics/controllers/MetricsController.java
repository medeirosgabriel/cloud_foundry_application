package com.ufcg.cfmetrics.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.cfmetrics.dto.PromRequestDTO;
import com.ufcg.cfmetrics.services.MetricService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
	
	@Autowired
	private MetricService metricService;
	
	@PostMapping("/")
	@Operation(description = "Get Prometheus Metrics")
	public ResponseEntity<?> getPrometheusMetrics(@RequestBody PromRequestDTO promRequest) throws IOException {
		return new ResponseEntity<String>(this.metricService.getPrometheusMetrics(promRequest), HttpStatus.OK);
    }
}
